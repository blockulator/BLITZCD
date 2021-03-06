;creating Delauney/Voronoi (sic) diagrams..

#del=On
#vor=On

BitMap 0,320,256,3
BitMap 1,320,256,3

BLITZ
InitCopList 0,3
CreateDisplay 0

InitPalette 0,8
PalRGB 0,0,0,5,3
PalRGB 0,1,12,12,12
PalRGB 0,2,15,15,15
PalRGB 0,3,0,0,15

DisplayPalette 0,0

NEWTYPE .pt
  x.q:y.q
End NEWTYPE

NEWTYPE .lin
  stx.q:sty.q
  farx.q:fary.q
  delx.q:dely.q
  a.q:b.q
  i.w:j.w
End NEWTYPE

Dim pt.pt(100),lin.lin(1000)
lins=-1

#pts=10

Gosub Create
Mouse On


.Main
Repeat
  VWait:DisplayBitMap 0,db:db=1-db
  Use BitMap db:Cls:BitMapOutput db:Locate 0,0:rev=0

  pt(#pts)\x=MouseX,MouseY
  Gosub Draw

Until Joyb(0)<>0
End



.Create
  For i.w=0 To #pts

    x=Rnd*100-50+160:y=Rnd*100+50
    Repeat
      x=Rnd*320:y=Rnd*200:happy=On
      For j.w=0 To i-1
        If QAbs(x-pt(j)\x)<25
          If QAbs(y-pt(j)\y)<25 Then happy=Off
        EndIf
      Next
    Until happy

    Select i
;      Case 0:x=100:y=100
;      Case 1:x=170:y=150
;      Case 2:x=150:y=20
;      Case 3:x=160:y=80
;      Case 4:x=MouseX:y=MouseY
    End Select
;    Plot x,y,i+1
    pt(i)\x=x,y
    *q.pt=pt(i)
  Next
Return




.Draw
  lins=-1
  For i.w=0 To #pts
    *p.pt=pt(i):x=*p\x:y=*p\y
    For j=0 To i-1
      *p.pt=pt(j)

      lins+1:*l.lin=lin(lins)
      *l\stx=(x+*p\x) ASR 1,(y+*p\y) ASR 1

      dx=y-*p\y:dy=*p\x-x
      dist=Sqr(dx*dx+dy*dy)
      *l\delx=dx/dist,dy/dist
      *l\farx=*l\stx+dx*650,*l\sty+dy*650
      *l\a=-640,640
      *l\i=i,j
    Next
  Next


  For k=#pts To 0 Step -1

    For h=1 To lins
      *h.lin=lin(h)
      If *h\i=k OR *h\j=k
        For g=0 To h-1
          *g.lin=lin(g)
          If *g\i=k OR *g\j=k
            rev+1

            dx=*h\stx-*g\stx
            dy=*h\sty-*g\sty
            det=*g\delx**h\dely-*g\dely**h\delx

            u=(dx**g\dely-dy**g\delx)/det
            v=(dx**h\dely-dy**h\delx)/det




            If *g\i=k
              *q.pt=pt(*g\j):high=On
            Else
              *q.pt=pt(*g\i):high=Off
            EndIf

            If *h\j=k Then high=NOT high

            dx=*q\x-*h\stx
            dy=*q\y-*h\sty

            If dx**h\delx+dy**h\dely<0 Then high=NOT high
            If high
              If v>*g\a Then *g\a=v
            Else
              If v<*g\b Then *g\b=v
            EndIf


            If *h\i=k
              *q.pt=pt(*h\j):high=On
            Else
              *q.pt=pt(*h\i):high=Off
            EndIf

            If *g\j=k Then high=NOT high

            dx=*q\x-*g\stx
            dy=*q\y-*g\sty

            If dx**g\delx+dy**g\dely<0 Then high=NOT high
            If high
              If u>*h\a Then *h\a=u
            Else
              If u<*h\b Then *h\b=u
            EndIf



          EndIf
        Next
      EndIf
    Next
  Next

  For l=0 To lins
    *l=lin(l)
    Gosub DrawLin
  Next
Return


.DrawLin
  If *l\b>*l\a
    sx=*l\stx+*l\delx**l\a
    sy=*l\sty+*l\dely**l\a
    fx=*l\stx+*l\delx**l\b
    fy=*l\sty+*l\dely**l\b
    CNIF #vor
      Line sx,sy,fx,fy,2
    CEND
    CNIF #del
      Line pt(*l\i)\x,pt(*l\i)\y,pt(*l\j)\x,pt(*l\j)\y,1
    CEND
  EndIf

Return
