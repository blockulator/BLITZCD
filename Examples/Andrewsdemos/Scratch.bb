; work in progress...
;   metaballs type thing..
;  move mouse left/right
; number in top left is frame rate..
;   I Get "1" with one ball, AND "2" when they're split..
;
BitMap 0,320,256,1
BitMap 1,320,256,1
InitCopList 0,44,256,1,0,2,0

PalRGB 0,0,10,10,10
PalRGB 0,1,0,0,0

BLITZ
CreateDisplay 0
DisplayPalette 0,0

mas1=0.3;15
mas2=0.15
pp2=-0.75

;mouse on
SetInt 5
  fr.w+1
  spd=0.003
  If pp2>-0.1 Then spd=0
  pp2+spd
End SetInt

Colour 1

Mouse On
Repeat
;  MOVE #0,$dff180
  BitMapOutput db
  Locate 3,3:Print fr:fr=0
  VWait:DisplayBitMap 0,db:db=1-db:Use BitMap db:Cls

;  mas2=MouseY*0.01+0.01
  pos2=MouseX/200;512

;  pos2=pp2

  pos1=0;-pos2
  For sy=0 To 255
    x=(sy-127.5) ASR 6

     ;gosub calcit

    xp1=(x-pos1)*(x-pos1)
    xp2=(x-pos2)*(x-pos2)
    ptc=(xp1-mas1)*xp2-mas2*xp1

;    ptc=x*x       *x*x-2pos2*x*x*x+pos2*pos2*x*x
;        -   (2*pos1*x* x*x-2*pos1*x*2pos2*x+2*pos1*x*pos2*pos2)
;        +pos1*pos1 *x*x-pos1*pos1 *2pos2*x+pos1*pos1 *pos2*pos2)
;         -mas1*x*x +2*mas1*pos2*x-pos2*pos2*mas1
;         -mas2*x*x +2*mas2*pos1*x-pos1*pos1*mas2

    If ptc<0 ;descr>(ptb*ptb)
      ptb=xp2+xp1 -mas1-mas2

      descr=ptb*ptb-ptc ASL 2

      y=Sqr((Sqr(descr)-ptb )*0.5)
      sx=y ASL 6
;      Plot sx,128- sy,1
;      Plot sx,128+ sy,1
      Line 160-sx,sy,160+sx,sy,1
;    Else
;      y=0
    EndIf

;    If y>0
;      sy=y ASL 6
;      Plot sx,128- sy,1
;      Plot sx,128+ sy,1
;      Line sx,128+ sy,sx,128-sy,1
;      Line sx,128-sy,1
;    EndIf

  Next

Until Joyb(0)<>0
End


calcit
    ;mas1*(x-pos2)*(x-pos2) +mas2*x*x -x*x*(x-pos2)*(x-pos2)
    ;     =     +( (x-pos2)^2+x*x  -mas1-mas2)*y*y +y*y*y*y

    ptc=(x-pos1)*(x-pos1)*(x-pos2)*(x-pos2)
    ptc-mas1*(x-pos2)*(x-pos2)
    ptc-mas2*(x-pos1)*(x-pos1)
    ptb=( (x-pos2)*(x-pos2)+(x-pos1)*(x-pos1) -mas1-mas2)
    pta=1

    descr=ptb*ptb-4*pta*ptc
    If descr>0
      scy=(Sqr(descr)-ptb )/2/pta
      If scy>0 Then y=Sqr(scy)
    EndIf
Return
