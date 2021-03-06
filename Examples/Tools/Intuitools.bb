;
WBStartup:NoCli
;
;A window making programming
;
.agadget
.
NEWTYPE.agadget
;
gt.w      ;0=text, 1=string, 2=prop, 3=shape
gx        ;gadget x       ;not including border!
gy        ;gadget y
gw        ;gadget width
gh        ;gadget height
gi        ;gadget ID
gf        ;gadget flags
;
;for text...
tg_text$
tg_flags.w  ;bit 15=1 if toggle on by default
          ;bit 14=1 if border
tg_jam
tg_c1
tg_c2
tg_b1
tg_b2
tg_bx
tg_by
;
;for string...
sg_text$    ;default text or none.
sg_length.w ;maximum characters in string.
sg_flags    ;<>0 if border!
sg_b1
sg_b2
sg_bx
sg_by
;
;for prop...
pg_xset.q
pg_yset
;
;for shape...
ig_file$
ig_flags.w
;
;for window text...
;
wt_text$
wt_jam.w
wt_c1
wt_c2
;
End NEWTYPE

DEFTYPE.agadget temp

MaxLen spa$=160:MaxLen sfi$=64
MaxLen bpa$=160:MaxLen bfi$=64
MaxLen fpa$=160:MaxLen ffi$=64

Dim List gads.agadget(100)

Dim fb.w(10)

For k=0 To 10:Read fb(k):Next
Data.w 0,1,2,3
Data.w 8,11,10,12
Data.w 4,5,6

USEPATH gads()

MenuTitle 0,0,"PROJECT"
  MenuItem 0,0,0,0,"LOAD","L"
  MenuItem 0,0,0,1,"SAVE","V"
  MenuItem 0,0,0,2,"CREATE SOURCE     ","C"
  MenuItem 0,0,0,3,"QUIT","Q"

MenuTitle 0,1,"SCREEN"
  MenuItem 0,0,1,0,"PALETTE"
;  MenuItem 0,0,1,1,"2.0 SCREEN PENS"
  MenuItem 0,0,1,1,"OPTIONS     ","O"

MenuTitle 0,2,"WINDOW"
  MenuItem 0,0,2,0,"ADD TEXT     ","A"
  MenuItem 0,0,2,1,"TEST"
  MenuItem 0,0,2,2,"OPTIONS","W"

MenuTitle 0,3,"GADGETS"
  MenuItem 0,0,3,0,"TEXT","T"
  MenuItem 0,0,3,1,"STRING     ","S"
  MenuItem 0,0,3,2,"PROP","P"
  MenuItem 0,0,3,3,"SHAPE","I"

Dim togg$(2)
togg$(0)="NO        "
togg$(1)="YES -> OFF"
togg$(2)="YES -> ON "

Dim jam$(3)
jam$(0)="JAM1      "
jam$(1)="JAM2      "
jam$(2)="COMPLEMENT"
jam$(3)="INVERSVID "

Dim yn$(1)
yn$(0)="YES"
yn$(1)="NO "

Dim lr$(1)
lr$(0)="LEFT "
lr$(1)="RIGHT"

Dim tb$(1)
tb$(0)="TOP   "
tb$(1)="BOTTOM"

.initial
sti$="SCREEN/WINDOW/GADGET PLANNER"
sd=1:sb=2
sm=0:de=2:mw=320:mh=DispHeight
ww=mw LSR 1:wh=mh LSR 1:wx=mw*.25:wy=mh*.25
Dim sp.w(5)
For k=0 To 5:Read sp(k):Next
Data.w 1,2,1,2,3,3
cc=1

wf.l=15+$1000:wt$="WINDOW":wd=1:wb=2
minw=32:minh=32:maxw=-1:maxh=-1

ngads=0

;text gadget create...
;
StringGadget 0,128,16,0,1,80,160
TextGadget 0,128,30,0,2,"          "
StringGadget 0,128,44,0,3,3,24
  SetString 0,3,"1"
StringGadget 0,128,58,0,4,3,24
  SetString 0,4,"0"
TextGadget 0,128,72,0,5,"          "
TextGadget 0,128,86,0,6,"   "
StringGadget 0,128,100,0,7,3,24
  SetString 0,7,"1"
StringGadget 0,128,114,0,8,3,24
  SetString 0,8,"2"
StringGadget 0,128,128,0,9,4,32
  SetString 0,9,"4"
StringGadget 0,128,142,0,10,4,32
  SetString 0,10,"2"
TextGadget 0,8,172,0,100," MAKE "
TextGadget 0,256,172,0,101," EXIT "

;string gadget create...
;
StringGadget 1,128,16,0,1,256,160
StringGadget 1,128,30,0,2,9,72
  SetString 1,2,"40"
TextGadget 1,128,44,0,3,"   "
StringGadget 1,128,58,0,4,3,24
  SetString 1,4,"1"
StringGadget 1,128,72,0,5,3,24
  SetString 1,5,"2"
StringGadget 1,128,86,0,6,4,32
  SetString 1,6,"4"
StringGadget 1,128,100,0,7,4,32
  SetString 1,7,"2"
TextGadget 1,8,172,0,100," MAKE "
TextGadget 1,256,172,0,101," EXIT "

;prop gadget...
;
TextGadget 2,128,16,0,1,"   "
TextGadget 2,128,30,0,2,"   "
TextGadget 2,128,44,0,3,"   "
StringGadget 2,128,58,0,4,4,32
  SetString 2,4,"1"
StringGadget 2,184,58,0,5,4,32
  SetString 2,5,"2"
StringGadget 2,128,72,0,6,4,32
  SetString 2,6,"1"
StringGadget 2,184,72,0,7,4,32
  SetString 2,7,"2"
TextGadget 2,128,86,0,8,"   "
TextGadget 2,128,100,0,9,"   "
TextGadget 2,8,172,0,100," MAKE "
TextGadget 2,256,172,0,101," EXIT "

;shape gadget...
;
TextGadget 3,128,16,0,1,"          "
TextGadget 3,128,30,0,2,"   "
TextGadget 3,8,172,0,100," MAKE "
TextGadget 3,256,172,0,101," EXIT "

;make gadget...
;
StringGadget 10,32,156,0,1,6,48
TextGadget 10,144,156,0,2,"     "
TextGadget 10,256,156,0,3,"      "
;
TextGadget 10,8,172,0,100," MAKE "
TextGadget 10,256,172,0,101," EXIT "

;create source
;
StringGadget 15,128,16,0,1,5,40
  SetString 15,1,"0"
StringGadget 15,128,30,0,2,5,40
  SetString 15,2,"0"
StringGadget 15,128,44,0,3,5,40
  SetString 15,3,"0"
TextGadget 15,128,58,0,4,"   "
TextGadget 15,128,72,0,5,"   "
TextGadget 15,128,86,0,6,"   "
;
TextGadget 15,8,172,0,100," MAKE "
TextGadget 15,256,172,0,101," EXIT "

Statement p{a$}
WLocate 4,WCursY+6
NPrint a$
End Statement

Statement q{a$}
WLocate 164,WCursY-8
NPrint a$
End Statement

Statement u{a$,a}
WLocate 128,a*14-7
Print a$
End Statement

Statement t{a$}
u{a$,GadgetHit}
End Statement

Statement w{a$,a}
Window 2,0,12,320,188,$1008,a$,1,2,a
End Statement

AddIDCMP 16:pg_v=1:pg_b=1:wr=1:hr=1

PalRGB 1,0,6,6,6:PalRGB 1,1,15,15,15:PalRGB 1,2,0,0,0:PalRGB 1,3,0,4,8
For k=4 To mc-1
PalRGB 1,k,Rnd(15),Rnd(15),Rnd(15)
Next
PalRGB 1,k,0,6,15

Dim sm$(3)
sm$(0)="LORES -> NO INTERLACE"
sm$(1)="HIRES -> NO INTERLACE"
sm$(2)="LORES -> INTERLACE   "
sm$(3)="HIRES -> INTERLACE   "

.newscreen
;
If sm>1 Then in=1 Else in=0
If sm&1 Then hi=1 Else hi=0
;
If hi Then fl=$8000:mw=640 Else fl=0:mw=320
If in Then fl|4
mc=1 LSL de-1:mc2=mc:If mc2>31 Then mc2=31
;
Buffer 0,mw LSR 3*mh*de+1024
;
;add text to window...
;
BitMap 1,mw,8,1
;
Free GadgetList 4
;
StringGadget 4,80,16,0,1,mw LSR 3,224
StringGadget 4,80,30,0,2,3,24
  SetString 4,2,"1"
StringGadget 4,80,44,0,3,3,24
  SetString 4,3,"0"
TextGadget 4,80,58,0,4,"          "
;
TextGadget 4,8,172,0,100," MAKE "
TextGadget 4,256,172,0,101," EXIT "
;
;window options..
;
Free GadgetList 12
;
StringGadget 12,96,16,0,1,160,208
  SetString 12,1,wt$
StringGadget 12,96,30,0,4,5,40
  SetString 12,4,Str$(wd)
StringGadget 12,264,30,0,5,5,40
  SetString 12,5,Str$(wb)
;
StringGadget 12,96,44,0,6,5,40
StringGadget 12,264,44,0,7,5,40
;
StringGadget 12,96,58,0,8,5,40
StringGadget 12,264,58,0,9,5,40
;
StringGadget 12,96,72,0,10,5,40
  SetString 12,10,Str$(minw)
StringGadget 12,264,72,0,11,5,40
  SetString 12,11,Str$(minh)
;
StringGadget 12,96,86,0,12,5,40
  SetString 12,12,Str$(maxw)
StringGadget 12,264,86,0,13,5,40
  SetString 12,13,Str$(maxh)
;
TextGadget 12,8,104,1,14,  " SIZING "
TextGadget 12,84,104,1,15, "  DRAG  "
TextGadget 12,160,104,1,16," DEPTH  "
TextGadget 12,236,104,1,17," CLOSE  "
;
TextGadget 12,8,118,1,18,  "BACKDROP"
TextGadget 12,84,118,1,19, "NOBORDER"
TextGadget 12,160,118,1,20,"GIMME 00"
TextGadget 12,236,118,1,21,"ACTIVATE"
;
TextGadget 12,8,132,1,22,  "   SIZE RIGHT    "
TextGadget 12,160,132,1,23,"   SIZE BOTTOM   "
;
TextGadget 12,84,146,1,24, " SIMPLE REFRESH  "

TextGadget 12,8,172,0,100," DONE "

For k=0 To 10
If wf BitTst fb(k)
  Toggle 12,14+k,On
Else
  Toggle 12,14+k,Off
EndIf
Next

;screen options
;
Free GadgetList 13
;
StringGadget 13,96,16,0,1,160,208
  SetString 13,1,sti$
StringGadget 13,96,30,0,2,3,24
  SetString 13,2,Str$(sd)
StringGadget 13,96,44,0,3,3,24
  SetString 13,3,Str$(sb)
TextGadget 13,96,58,0,4,"                     "
StringGadget 13,96,72,0,5,4,32
  SetString 13,5,Str$(mh)
StringGadget 13,96,86,0,6,3,24
  SetString 13,6,Str$(de)
TextGadget 13,8,172,0,100," DONE "
;
;make palette requester gadgets
;
Free GadgetList 14:y=0
;
GadgetJam 1
For k=0 To mc
If k&15=0 Then x=-2:y+20
x+18
GadgetPens 0,k
TextGadget 14,x,y,0,k+1," "
Next
GadgetPens 1,0
;
PropGadget 14,60,100,64,65,252,12
PropGadget 14,60,116,64,66,252,12
PropGadget 14,60,132,64,67,252,12
;
TextGadget 14,8,172,0,100," OKAY "
TextGadget 14,240,172,0,101," CANCEL "
;
ScreenPens sp(0),sp(1),sp(2),sp(3),sp(4),sp(5),sp(5)
Screen 0,0,0,mw,mh,de,fl,sti$,sd,sb
Use Palette 1
Window 0,0,0,mw,mh,$1900,"",1,2
ScreensBitMap 0,0
SetMenu 0
newsc=0
Gosub shownew

.main
If newsc
  Free Window 0
  Free Window 1
  Free Window 2
  FlushEvents
  Goto newscreen
EndIf
ev.l=WaitEvent:Use Window EventWindow
Select ev
Case 256
  Select MenuHit
  Case 0
    Select ItemHit
    Case 0  ;load
      Gosub doload:newsc=-1
    Case 1  ;save
      Gosub dosave
    Case 2  ;create source
      Gosub dosource
    Case 3
      End
    End Select
  Case 1  ;screen
    Select ItemHit
    Case 0  ;palette
      Gosub dopalette
    Case 1
      ;
      w{"SCREEN OPTIONS",13}
      WLocate 0,1
      p{"TITLE:"}
      p{"DETAIL PEN:"}
      p{"BLOCK PEN:"}
      p{"MODE:"}
      p{"HEIGHT:"}
      p{"DEPTH:"}
      ;
      WLocate 96,49:Print sm$(sm)
      ;
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        Select ev
        Case 64
          Select GadgetHit
          Case 4
            sm+1 AND 3
            mh=Val(StringText$(13,5))
            de=Val(StringText$(13,6))
            If sm=2 Then mh*2:Gosub checkmh:Gosub showmh
            If sm=0 Then mh/2:Gosub checkmh:Gosub showmh
            Gosub checkde
            WLocate 96,49:Print sm$(sm)
          Case 5
            mh=Val(StringText$(13,5))
            Gosub checkmh
          Case 6
            de=Val(StringText$(13,6))
            Gosub checkde
          Case 100
            sti$=StringText$(13,1)
            sd=Val(StringText$(13,2))
            sb=Val(StringText$(13,3))
            mh=Val(StringText$(13,5))
            de=Val(StringText$(13,6))
            Gosub checkmh
            Gosub checkde
            mw=320:If sm&1 Then mw=640
            Gosub sussw:ex=-1
          End Select
        End Select
      Until ex
      newsc=-1
    End Select
  Case 2
    Select ItemHit
    Case 0
      ClearString 4,1
      w{"ADD WINDOW TEXT",4}
      WLocate 0,1
      p{"TEXT:"}
      p{"COLOUR 1:"}
      p{"COLOUR 2:"}
      p{"JAM MODE:"}
      WLocate 80,4*14-7:Print jam$(wt_jam)
      ActivateString 2,1
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        Select ev
        Case 64
          Select GadgetHit
          Case 4
            wt_jam+1 AND 3
            WLocate 80,4*14-7:Print jam$(wt_jam)
          Case 100
            If AddLast(gads())
              ;
              c1=Val(StringText$(4,2))
              c2=Val(StringText$(4,3))
              Gosub fw2
              ;
              text$=StringText$(4,1)
              gw=Len(text$)LSL 3:gh=8
              ;
              BitMapOutput 1:Locate 0,0:Print text$
              ;
              Use BitMap 1
              GetaShape 0,0,0,gw,gh
              Use BitMap 0
              ;
              Gosub posit2
              ;
              \gt=4,gx,gy,gw,gh,-1
              \wt_text=text$,wt_jam,c1,c2
              ;
              Gosub maketext
              ;
            EndIf
            ex=-1
          Case 101
            ex=-1
          End Select
        End Select
      Until ex
      Gosub fw2:Use Window 0
    Case 1  ;test...
      Gosub viewwin2
      Window 2,0,0,320,16,14,"CLOSE ME TO EXIT TEST MODE!",1,2
      Repeat
      Until WaitEvent=512 AND EventWindow=2
      Free Window 2:Gosub shownew
    Case 2  ;window options
      SetString 12,6,Str$(wx)
      SetString 12,7,Str$(wy)
      SetString 12,8,Str$(ww)
      SetString 12,9,Str$(wh)
      ;
      w{"WINDOW OPTIONS",12}
      WLocate 0,1
      p{"TITLE:"}
      p{"DETAIL PEN:"}
      q{"BLOCK PEN:"}
      p{"X:"}
      q{"Y:"}
      p{"WIDTH:"}
      q{"HEIGHT:"}
      p{"MIN WIDTH:"}
      q{"MIN HEIGHT:"}
      p{"MAX WIDTH:"}
      q{"MAX HEIGHT:"}
      ;
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        ;
        Select ev
        Case 64
          If GadgetHit>13 AND GadgetHit<25
            wf BitChg fb(GadgetHit-14)
          EndIf
          Select GadgetHit
          Case 100
            ;
            wt$=StringText$(12,1)
            wd=Val(StringText$(12,4))
            wb=Val(StringText$(12,5))
            wx=Val(StringText$(12,6))
            wy=Val(StringText$(12,7))
            ww=Val(StringText$(12,8))
            wh=Val(StringText$(12,9))
            minw=Val(StringText$(12,10))
            minh=Val(StringText$(12,11))
            maxw=Val(StringText$(12,12))
            maxh=Val(StringText$(12,13))
            ;
            Gosub sussw
            Gosub qwin
            ;
            ex=-1
          End Select
        End Select
      Until ex
      ;
      Free Window 2
      Gosub shownew
      Use Window 0
      ;
    End Select
  Case 3
    Select ItemHit
    Case 0
      ;
      ClearString 0,1
      w{"TEXT GADGET PARAMETERS",0}
      WLocate 0,1
      p{"TEXT:"}
      p{"TOGGLE?:"}
      p{"COLOUR 1:"}
      p{"COLOUR 2:"}
      p{"JAM MODE:"}
      p{"BORDERS?:"}
      p{"HILIGHT COLOUR:"}
      p{"SHADOW COLOUR:"}
      p{"BORDER X:"}
      p{"BORDER Y:"}
      ;
      u{togg$(tg_togg),2}
      u{jam$(tg_jam),5}
      u{yn$(tg_bo),6}
      ;
      ActivateString 2,1
      ;
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        Select ev
        Case 64
          Select GadgetHit
          Case 2  ;toggle...
            tg_togg+1:If tg_togg=3 Then tg_togg=0
            t{togg$(tg_togg)}
          Case 5
            tg_jam+1 AND 3
            t{jam$(tg_jam)}
          Case 6
            tg_bo+1 AND 1
            t{yn$(tg_bo)}
          Case 100  ;make
            ;
            Gosub prepmake
            ;
            ;now, to draw up gadget!
            ;
            text$=Replace$(StringText$(0,1),"_"," ")
            bx=Val(StringText$(0,9))
            by=Val(StringText$(0,10))
            b1=Val(StringText$(0,7))
            b2=Val(StringText$(0,8))
            c1=Val(StringText$(0,3))
            c2=Val(StringText$(0,4))
            ;
            gw=Len(text$) LSL 3
            gh=8
            ;
            gx=160-gw LSR 1:gy=88-gh LSR 1
            ;
            If tg_bo=0
              BorderPens b1,b2
              Borders bx,by
              GadgetBorder gx,gy,gw,gh
            EndIf
            ;
            WJam tg_jam:WColour c1,c2
            WLocate gx-WLeftOff,gy-WTopOff:Print text$
            ;
            If tg_bo=0
              gx-bx:gy-by:gw+bx+bx:gh+by+by
            EndIf
            ;
            WJam 1:WColour 1,0
            ;
            Gosub makeit
            ;
            If ex>0
              If AddLast(gads())
                Gosub posit
                .
                \gt=0,gx,gy,gw,gh,gi,gf
                ;
                k=0:If tg_togg Then k=1:If tg_togg=2 Then k BitSet 15
                If tg_bo=0 Then k BitSet 14
                \tg_text=text$,k,tg_jam,c1,c2,b1,b2,bx,by
                ;
                Gosub shownew
                ;
              EndIf
            EndIf
            ;
          Case 101  ;cancel
            ex=-1
          End Select
        Case 512
          ex=-1
        End Select
      Until ex
      Gosub fw2
      Use Window 0
      ;
    Case 1
      ;
      w{"STRING GADGET PARAMETERS",1}
      WLocate 0,1
      p{"TEXT:"}
      p{"MAX LENGTH:"}
      p{"BORDERS?:"}
      p{"HILIGHT COLOUR:"}
      p{"SHADOW COLOUR:"}
      p{"BORDER X:"}
      p{"BORDER Y:"}
      ;
      u{yn$(sg_bo),3}
      ;
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        Select ev
        Case 64
          Select GadgetHit
          Case 1
            If Len(StringText$(1,1))=>Val(StringText$(1,2))
              SetString 1,2,Str$(Len(StringText$(1,1))+1)
              Redraw 2,2
            EndIf
          Case 2
            k=Val(StringText$(1,2))
            If k
              If k<=Len(StringText$(1,1))
                SetString 1,1,Left$(StringText$(1,1),k-1)
                Redraw 2,1
              EndIf
            Else
              SetString 1,2,"1":Redraw 2,2
            EndIf
          Case 3
            sg_bo+1 AND 1
            t{yn$(sg_bo)}
          Case 100  ;make
            ;
            Gosub prepmake
            ;
            text$=Replace$(StringText$(1,1),"_"," ")
            ml=Val(StringText$(1,2)):If ml=0 Then ml=1
            bx=Val(StringText$(1,6))
            by=Val(StringText$(1,7))
            b1=Val(StringText$(1,4))
            b2=Val(StringText$(1,5))
            ;
            gw=8
            gh=8
            ;
            gx=160-gw LSR 1:gy=88-gh LSR 1
            ;
            If sg_bo=0
              BorderPens b1,b2
              Borders bx,by
              GadgetBorder gx,gy,gw,gh
              gx-bx:gy-by:gw+bx+bx:gh+by+by
            EndIf
            ;
            Gosub makeit
            ;
            If ex>0
              If AddLast(gads())
                Gosub posit
                ;
                xl=gx+gw-1+wx:yl=gy+gh-1+wy
                ;
                x=SMouseX:y=gy+gh-1:Gosub showabox
                Repeat
                  WaitEvent:Use Window EventWindow
                  Gosub showabox
                  x=SMouseX
                  Gosub showabox
                Until MButtons=1
                Gosub showabox
                .
                .makestring
                .
                gw=x-wx-gx+1
                ;
                \gt=1,gx,gy,gw,gh,gi,gf
                ;
                \sg_text=text$,ml,1-sg_bo,b1,b2,bx,by
                ;
                Gosub shownew
                ;
              EndIf
            EndIf
            ;
          Case 101  ;cancel
            ex=-1
          End Select
        Case 512
          ex=-1
        End Select
      Until ex
      Gosub fw2
      Use Window 0
      ;
    Case 2
      ;
      w{"PROPORTIONAL GADGET PARAMETERS",2}
      WLocate 0,1
      p{"HOIRZONTAL?:"}
      p{"VERTICAL?:"}
      p{"BORDER?:"}
      p{"X SETTING:"}
      WLocate 168,WCursY-8
      NPrint "/"
      p{"Y SETTING:"}
      WLocate 168,WCursY-8
      NPrint "/"
      p{"WIDTH REL:"}
      p{"HEIGHT REL:"}
      ;
      u{yn$(pg_h),1}
      u{yn$(pg_v),2}
      u{yn$(pg_b),3}
      u{yn$(wr),6}
      u{yn$(hr),7}
      ;
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        Select ev
        Case 64
          Select GadgetHit
          Case 1
            pg_h+1 AND 1
            t{yn$(pg_h)}
          Case 2
            pg_v+1 AND 1
            t{yn$(pg_v)}
          Case 3
            pg_b+1 AND 1
            t{yn$(pg_b)}
          Case 5
            If Val(StringText$(2,5))
            Else
              SetString 2,5,"1":Redraw 2,5
            EndIf
          Case 7
            If Val(StringText$(2,7))
            Else
              SetString 2,7,"1":Redraw 2,7
            EndIf
          Case 8
            wr+1 AND 1
            u{yn$(wr),6}
          Case 9
            hr+1 AND 1
            u{yn$(hr),7}
          Case 100  ;make
            ;
            Gosub prepmake
            ;
            xset.q=Val(StringText$(2,4))/Val(StringText$(2,5))
            yset.q=Val(StringText$(2,6))/Val(StringText$(2,7))
            ;
            gw=6
            gh=6
            ;
            gx=160-gw LSR 1:gy=88-gh LSR 1
            ;
            WBox gx,gy,gx+gw-1,gy+gh-1,1
            ;
            Gosub makeit
            ;
            If ex>0
              If AddLast(gads())
                Gosub posit
                ;
                xl=gx+5+wx:yl=gy+5+wy
                ;
                Gosub showabox2
                Repeat
                  WaitEvent:Use Window EventWindow
                  Gosub showabox
                  Gosub showabox2
                Until MButtons=1
                Gosub showabox
                gw=x-wx-gx+1:gh=y-wy-gy+1
                .
                .makeprop
                .
                k=0
                If pg_h=0 Then k=64
                If pg_v=0 Then k|128
                If pg_b Then k|256
                If wr=0 Then k|8
                If hr=0 Then k|16
                ;
                \gt=2,gx,gy,gw,gh,gi,gf|k
                ;
                \pg_xset=xset,yset
                ;
                Gosub shownew
                ;
              EndIf
            EndIf
            ;
          Case 101  ;cancel
            ex=-1
          End Select
        Case 512
          ex=-1
        End Select
      Until ex
      Gosub fw2
      Use Window 0
      ;
    Case 3
      ;
      w{"SHAPE GADGET PARAMETERS",3}
      WLocate 0,1
      p{"TOGGLE?:"}
      p{"USE PALETTE?:"}
      ;
      u{togg$(ig_togg),1}
      u{yn$(ig_up),2}
      ;
      ex=0
      Repeat
        Repeat
          ev=WaitEvent
        Until EventWindow=2
        Select ev
        Case 64
          Select GadgetHit
          Case 1
            ig_togg+1:If ig_togg=3 Then ig_togg=0
            t{togg$(ig_togg)}
          Case 2
            ig_up+1 AND 1
            t{yn$(ig_up)}
          Case 100  ;make
            ;
            text$=FileRequest$("IFF Brush name",bpa$,bfi$)
            ;
            If text$
              ;
              Gosub prepmake
              ;
              SetErr
                ClrErr
                DisplayBeep_ 0
                Pop Select:Pop Select
                Goto sfail
              End SetErr
              ;
              LoadShape 0,text$,0
              ClrErr
              If ig_up=0 Then Use Palette 0
              ;
              gw=ShapeWidth(0):gh=ShapeHeight(0)
              ;
              gx=160-gw LSR 1:gy=88-gh LSR 1
              ;
              sha.l=Addr Shape(0)
              bps=Peek.w(sha+4)
              If bps>de Then Poke.w sha+4,de
              ;
              Blit 0,gx,gy+12
              ;
              Poke.w sha+4,bps
              ;
              Gosub makeit
              ;
              If ex>0
                If AddLast(gads())
                  Gosub posit
                  .
                  .makeshape
                  .
                  \gt=3,gx,gy,gw,gh,gi,gf
                  ;
                  k=0
                  If ig_togg Then k=1:If ig_togg=2 Then k BitSet 15
                  ;
                  \ig_file=text$,k
                  ;
                  repsh=-1:Gosub shownew
                  ;
                EndIf
              EndIf
              ;
            EndIf
          Case 101  ;cancel
            ex=-1
          End Select
        Case 512
          ex=-1
        End Select
      Until ex
      sfail
      Gosub fw2
      Use Window 0
      ;
    End Select
  End Select
Case 8
  If MButtons=1 ;left down
    ex=SMouseX:ey=SMouseY
    If ex>=wx AND ex<wx+ww AND ey>=wy AND ey<wy+wh
      ;
      If ex>=wx+ww-9 AND ey>=wy+wh-9
        ;
        ;size window!
        ;
        xo=ex-ww:yo=ey-wh
        ww2=ww:wh2=wh
        ;
        Repeat
          WaitEvent
          x=QLimit(SMouseX-xo,9,mw-wx)
          y=QLimit(SMouseY-yo,9,mh-wy)
          Gosub showwin
          ww=x:wh=y
          Gosub showwin
        Until MButtons=5
        ;
        inw+(ww-ww2):inh+(wh-wh2)
        ;
        ResetList gads()
        While NextItem(gads())
          If \gf BitTst 1 ;rel rite?
            \gx-ww2+ww
          EndIf
          If \gf BitTst 2 ;rel bot?
            \gy-wh2+wh
          EndIf
          If \gf BitTst 3 ;rel width?
            \gw-ww2+ww
          EndIf
          If \gf BitTst 4 ;rel hite?
            \gh-wh2+wh
          EndIf
        Wend
        ;
        Gosub showwin:Gosub shownew
        ;
      Else
        ;possible gadget move!
        ResetList gads()
        While NextItem(gads())
          If ex>=\gx+wx AND ex<\gx+\gw+wx AND ey>=\gy+wy AND ey<\gy+\gh+wy
            ;move a gadget!
            ;
            src.l=&gads():des.l=&temp
            Gosub copygad:src=des
            ;
            GetaShape 0,\gx+wx,\gy+wy,\gw,\gh
;            Boxf \gx+wx,\gy+wy,\gx+\gw+wx-1,\gy+\gh+wy-1,0
            ;
            If \gt=3 Then repsh=-1
            ni=\gi:KillItem gads()
            ;
            Window 2,0,0,320,16,14,"PASTE MODE - RIGHT BUTTON TO EXIT",1,2
            ;
            Gosub shownew
            ;
            Gosub findhi
            ;
            Repeat
              Gosub posit2
              If mb=1
                If AddItem(gads())
                  des=&gads()
                  Gosub copygad
                  \gx=gx,gy:\gi=ni
                  ni+1:If ni<=hni Then ni=hni+1
                  Gosub shownew
                EndIf
              EndIf
            Until mb=2
            Free Window 2
            Gosub shownew:Goto bdone
          EndIf
        Wend
        ;
        ;move window around!
        ;
        xo=ex-wx:yo=ey-wy
        Repeat
          WaitEvent
          x=QLimit(SMouseX-xo,0,mw-ww)
          y=QLimit(SMouseY-yo,0,mh-wh)
          Gosub showwin
          wx=x:wy=y
          Gosub showwin
        Until MButtons=5
        Gosub showwin:Gosub viewwin:Gosub showwin
        bdone
      EndIf
    EndIf
  EndIf
End Select
Goto main

;********************* SUBROUTINES ********************

sussw
If wx>=mw OR wx<0 OR wx+ww>mw OR wy>=mh OR wy<0 OR wy+wh>mh
  ww=Int(mw LSR 1):wh=Int(mh LSR 1)
  wx=Int(mw*.25):wy=Int(mh*.25)
EndIf
Return

qwin
FlushEvents
Window 1,wx,wy,ww,wh,wf,wt$,wd,wb
inw=InnerWidth:inh=InnerHeight
wlo=WLeftOff:wto=WTopOff
Return

Statement a{a.l}
Print Mkl$(a)
End Statement

Function.l b{}
Function Return Cvl(Inkey$(4))
End Function

.doload
f$=FileRequest$("Filename to load",fpa$,ffi$)
If f$
  If ReadFile(0,f$)
    FileInput 0
    ;
    sti$=Edit$(160)
    sd=b{}:sb=b{}:sm=b{}:mw=b{}:mh=b{}:de=b{}
    ;
    wf=b{}:wt$=Edit$(160)
    wd=b{}:wb=b{}:minw=b{}:minh=b{}:maxw=b{}:maxh=b{}
    wx=b{}:wy=b{}:ww=b{}:wh=b{}
    ;
    inw=b{}:inh=b{}:wlo=b{}:wto=b{}
    ;
    For k=0 To 5:sp(k)=b{}:Next
    ;
    For k=0 To 31:PalRGB 1,k,b{},b{},b{}:Next
    ;
    ClearList gads():ngads=b{}
    ;
    For k=1 To ngads
    If AddLast(gads())
      MaxLen \tg_text=0
      MaxLen \sg_text=0
      MaxLen \ig_file=0
      MaxLen \wt_text=0
      ReadMem 0,&gads(),SizeOf.agadget
      Poke.l &gads()+SizeOf.agadget\tg_text,0
      Poke.l &gads()+SizeOf.agadget\sg_text,0
      Poke.l &gads()+SizeOf.agadget\ig_file,0
      Poke.l &gads()+SizeOf.agadget\wt_text,0
      \tg_text=Edit$(256)
      \sg_text=Edit$(256)
      \ig_file=Edit$(256)
      \wt_text=Edit$(256)
    EndIf
    Next
    ;
    CloseFile 0:WindowInput 0
    ;
  Else
    DisplayBeep_ 0
  EndIf
EndIf
Return

.dosave
f$=FileRequest$("Filename to save",fpa$,ffi$)
If f$
  If WriteFile(0,f$)
    FileOutput 0
    ;
    NPrint sti$
    a{sd}:a{sb}:a{sm}:a{mw}:a{mh}:a{de}
    ;
    a{wf}:NPrint wt$
    a{wd}:a{wb}:a{minw}:a{minh}:a{maxw}:a{maxh}
    a{wx}:a{wy}:a{ww}:a{wh}
    ;
    a{inw}:a{inh}:a{wlo}:a{wto}
    ;
    For k=0 To 5:a{sp(k)}:Next
    ;
    For k=0 To 31:a{Red(k)}:a{Green(k)}:a{Blue(k)}:Next
    ;
    a{ngads}
    ;
    ResetList gads()
    While NextItem(gads())
      WriteMem 0,&gads(),SizeOf.agadget
      NPrint \tg_text
      NPrint \sg_text
      NPrint \ig_file
      NPrint \wt_text
    Wend
    ;
    CloseFile 0:WindowOutput 0
    ;
  Else
    DisplayBeep_ 0
  EndIf
EndIf
Return

dosource
w{"CREATE SOURCE CODE",15}
WLocate 0,1
p{"SCREEN#:"}
p{"WINDOW#:"}
p{"GADGETLIST#:"}
p{"SCREEN SOURCE?:"}
p{"WINDOW SOURCE?:"}
p{"GADGET SOURCE?:"}
;
u{yn$(ses),4}
u{yn$(wis),5}
u{yn$(gas),6}
;
ex=0
Repeat
  Repeat
    ev=WaitEvent
  Until EventWindow=2
  Select ev
  Case 64
    Select GadgetHit
    Case 4
      ses+1 AND 1
      t{yn$(ses)}
    Case 5
      wis+1 AND 1
      t{yn$(wis)}
    Case 6
      gas+1 AND 1
      t{yn$(gas)}
    Case 100
      scin=Val(StringText$(15,1))
      win=Val(StringText$(15,2))
      gan=Val(StringText$(15,3))
      ex=-1:Gosub makesource
    Case 101
      ex=-1
    End Select
  End Select
Until ex
Gosub fw2:Use Window 0
;
Return

.makesource ;create source code!
;
f$=FileRequest$("Filename for source code",spa$,sfi$)
If f$
  If WriteFile(0,f$)
    FileOutput 0
    If gas=0 Then Gosub dogadgets
    If ses=0 Then Gosub doscreen
    If wis=0 Then Gosub dowindow
    CloseFile 0
    WindowOutput 2
  Else
    DisplayBeep_ 0
  EndIf
EndIf
Return

.doscreen  ;write screen stuff!
NPrint ""
Print "Screen ",scin,",0,0,",mw,",",mh,",",de,",",fl,","
NPrint Chr$(34)+sti$+Chr$(34),",",sd,",",sb
j=0
For k=0 To mc
j+1
Print "RGB ",k,",",Red(k),",",Green(k),",",Blue(k)
If j&3=0 OR k=mc Then NPrint "" Else Print ":"
Next
;
Return

.dowindow
;
NPrint ""
NPrint "SizeLimits ",minw,",",minh,",",maxw,",",maxh
Print "Window ",win,",",wx,",",wy,",",ww,",",wh,",$"
Print StripLead$(Hex$(wf),48),",",Chr$(34),wt$,Chr$(34),",",wd,",",wb
If gas=0 Then Print ",",gan
NPrint ""
txtf=0:Gosub maketext2
;
Return

.dogadgets
NPrint ""
gadf=0:Gosub makeglist2
;
Return

.dopalette
Gosub setprops
w{"SCREEN PALETTE",14}
WLocate 8,98-7
NPrint "RED  :"
WLocate 8,WCursY+8
NPrint "GREEN:"
WLocate 8,WCursY+8
Print  "BLUE :"
Gosub showcc
For k=0 To mc2
PalRGB 2,k,Red(k),Green(k),Blue(k)
Next
ex=0
Repeat
  Repeat
    ev=WaitEvent
  Until EventWindow=2
  Select ev
  Case 64
    If GadgetHit=100
      ex=-1
      For k=0 To mc2
        PalRGB 1,k,Red(k),Green(k),Blue(k)
      Next
    EndIf
    If GadgetHit=101
      ex=-1
      Use Palette 2
    EndIf
    If GadgetHit<33
      Gosub hidecc:cc=GadgetHit-1:Gosub showcc
      Gosub setprops:Gosub showprops
    EndIf
    If GadgetHit>64 AND GadgetHit<68 Then Gosub readprops
  Case 32
    If GadgetHit>64 AND GadgetHit<68
      Repeat
        Gosub readprops
      Until WaitEvent=64
    EndIf
  End Select
Until ex
Gosub fw2:Use Window 0
Return

.hidecc
y=Int(cc LSR 4)*20+22+WTopOff
x=cc&15*18+16+WLeftOff
WBox x,y,x+7,y+2,0
Return

.showcc
y=Int(cc LSR 4)*20+22+WTopOff
x=cc&15*18+16+WLeftOff
WBox x,y,x+7,y+2,1
Return

.readprops
RGB cc,HPropPot(14,65)*16,HPropPot(14,66)*16,HPropPot(14,67)*16
Return

.setprops
SetHProp 14,65,Red(cc)/15,1/16
SetHProp 14,66,Green(cc)/15,1/16
SetHProp 14,67,Blue(cc)/15,1/16
Return

.showprops
Redraw 2,65:Redraw 2,66:Redraw 2,67:Return

findhi
hni=-1
ResetList gads()
While NextItem(gads())
  If \gi>hni Then hni=\gi
Wend
Return

copygad
For k=0 To SizeOf.agadget-2 Step 2
Poke.w des+k,Peek.w(src+k)
Next:Return

checkde
k=6:If sm&1 Then k=4
If de<1 Then de=1:Goto showde Else If de>k Then de=k:Goto showde
Return

showde
SetString 13,6,Str$(de)
Redraw 2,6:Return

checkmh
k=DispHeight:If sm>1 Then k*2
If mh<32 Then mh=32:Goto showmh Else If mh>k Then mh=k:Goto showmh
Return

showmh
SetString 13,5,Str$(mh)
Redraw 2,5:Return

.prepmake
Gosub findhi:If hni>0 Then gi=hni+1 Else gi=1
SetString 10,1,Str$(gi)
w{"MAKE GADGET",10}
WLocate 4,154-7:Print "ID:"
WLocate 92,154-7:Print "X REL:"
WLocate 204,154-7:Print "Y REL:"
;
WLocate 144,154-7:Print lr$(lr)
WLocate 256,154-7:Print tb$(tb)
;
Return

.posit ;position gadget!
;
GetaShape 0,gx,gy+12,gw,gh
;
Gosub fw2
;
posit2:Menus Off
;
Use Window 0:Activate 1
;
Gosub showwin:Gosub showgad
;
Repeat
  WaitEvent:Use Window EventWindow
  Gosub showgad
  mb=MButtons
Until mb=1 OR mb=2
UnBuffer 0
Menus On
Return

showgad
UnBuffer 0
gx=SMouseX:gy=SMouseY
If gx+ShapeWidth(0)>mw Then gx=mw-ShapeWidth(0)
If gy+ShapeHeight(0)>mh Then gy=mh-ShapeHeight(0)
BBlit 0,0,gx,gy
gx-wx:gy-wy
Return

.makeit
Repeat
  Repeat
    ev=WaitEvent
  Until EventWindow=2
  Select ev
  Case 64
    Select GadgetHit
    Case 2  ;left/right
      lr+1 AND 1
      WLocate 144,154-7:Print lr$(lr)
    Case 3  ;top/bottom
      tb+1 AND 1
      WLocate 256,154-7:Print tb$(tb)
    Case 100  ;make!
      gi=Val(StringText$(10,1))
      If gi=0 Then Gosub badid:Goto seldone
      ;
      ;check gadget ID doesn't already exist!
      ;
      ResetList gads()
      While NextItem(gads())
        If \gi=gi ;no can do!
          Gosub badid
          Goto seldone
        EndIf
      Wend
      ;
      gf=0:If lr Then gf=2
      If tb Then gf|4
      ex=1
      seldone
    Case 101
      gi-1:ex=-1
    End Select
  Case 512
    ex=-1
  End Select
Until ex
Return

badid ;bad gadget ID error message
;
DisplayBeep_ 0
SetString 10,1,"*BAD*"
Redraw 2,1
Return

handlejam
If gadf
  GadgetJam j:GadgetPens c1,c2
Else
  kz=0
  If j<>lj
    Print "GadgetJam ",j:kz=-1
  EndIf
  If c1<>lc1 OR c2<>lc2
    If kz Then Print ":"
    Print "GadgetPens ",c1,",",c2:kz=-1
  EndIf
  If kz Then NPrint ""
  lj=j:lc1=c1:lc2=c2
EndIf
Return

handlebord
If gadf
  Borders Off
  If k  ;border?
    Borders On
    BorderPens b1,b2:Borders bx,by
  EndIf
Else
  If lbo
    kz=0
    If bo
      If lbo<0 Then Print "Borders On":kz=-1
      If b1<>lb1 OR b2<>lb2
        If kz Then Print ":"
        Print "BorderPens ",b1,",",b2:kz=-1
        lb1=b1:lb2=b2
      EndIf
      If bx<>lbx OR by<>lby
        If kz Then Print ":"
        Print "Borders ",bx,",",by:kz=-1
        lbx=bx:lby=by
      EndIf
    Else
      If lbo>0 Then Print "Borders Off":kz=-1:lbo=-1
    EndIf
    If kz Then NPrint ""
  Else
    If bo
      NPrint "Borders On:BorderPens ",b1,",",b2,":Borders ",bx,",",by
      lbx=bx:lby=by:lb1=b1:lb2=b2:lbo=1
    Else
      NPrint "Borders Off"
      lbo=-1
    EndIf
  EndIf
EndIf
Return

.makeglist ;makegadgetlist 11!
;
Free GadgetList 11:Use Window 1:gadf=-1
;
makeglist2
;
lbx=-1:lby=-1:lb1=-1:lb2=-1:lj=-1:lc1=-1:lc2=-1
lbo=0
shn=0
ngads=0
;
ResetList gads()
  While NextItem(gads())
  ngads+1
  ;
  x=\gx:y=\gy
  ;
  If wf&$400
    x-wlo:y-wto
    ww2=inw:wh2=inh
  Else
    ww2=ww:wh2=wh
  EndIf
  ;
  If \gf BitTst 1 ;rel rite?
    x-ww2+1
  EndIf
  If \gf BitTst 2 ;rel bot?
    y-wh2+1
  EndIf
  ;
  Select \gt
  ;
  Case 0  ;text gadget
    ;
    j=\tg_jam:c1=\tg_c1:c2=\tg_c2
    Gosub handlejam
    ;
    bo=0 ;no borders!
    If \tg_flags BitTst 14  ;border?
      bx=\tg_bx:by=\tg_by:b1=\tg_b1:b2=\tg_b2:bo=-1
    EndIf
    Gosub handlebord
    ;
    If gadf
      TextGadget 11,x,y,\tg_flags&1|\gf,\gi,\tg_text
      If \tg_flags BitTst 15  ;toggle on?
        Toggle 11,\gi,On
      EndIf
    Else
      Print "TextGadget ",gan,",",x,",",y,",",\tg_flags&1|\gf,","
      NPrint \gi,",",Chr$(34),\tg_text,Chr$(34)
      If \tg_flags BitTst 15  ;toggle?
        NPrint "Toggle ",gan,",",\gi,",On"
      EndIf
    EndIf
    ;
  Case 1  ;string gadget
    k=\gw:bo=0
    If \sg_flags
      b1=\sg_b1:b2=\sg_b2:bx=\sg_bx:by=\sg_by:bo=-1
      k-\sg_bx-\sg_bx
    EndIf
    Gosub handlebord
    ;
    If gadf
      StringGadget 11,x,y,\gf,\gi,\sg_length,k
      If \sg_text
        SetString 11,\gi,\sg_text
      EndIf
    Else
      Print "StringGadget ",gan,",",x,",",y,",",\gf,",",\gi,","
      NPrint \sg_length,",",k
      If \sg_text
        NPrint "SetString ",gan,",",\gi,",",Chr$(34),\sg_text,Chr$(34)
      EndIf
    EndIf
    ;
  Case 2  ;prop gadget
    ;
    w=\gw:h=\gh
    If \gf BitTst 3 ;rel width?
      w-ww2
    EndIf
    If \gf BitTst 4 ;rel height?
      h-wh2
    EndIf
    ;
    If gadf
      PropGadget 11,x,y,\gf,\gi,w,h
    Else
      NPrint "PropGadget ",gan,",",x,",",y,",",\gf,",",\gi,",",w,",",h
    EndIf
    ;
    If \gf BitTst 6
      If gadf
        SetHProp 11,\gi,0,\pg_xset
      Else
        NPrint "SetHProp ",gan,",",\gi,",0,",\pg_xset
      EndIf
    EndIf
    If \gf BitTst 7
      If gadf
        SetVProp 11,\gi,0,\pg_yset
      Else
        NPrint "SetVProp ",gan,",",\gi,",0,",\pg_yset
      EndIf
    EndIf
    ;
  Case 3  ;shapegadget
    ;
    shn+1
    If gadf
      If repsh
        LoadShape shn,\ig_file
      EndIf
      ShapeGadget 11,x,y,\ig_flags&1|\gf,\gi,shn
      If \ig_flags BitTst 15
        Toggle 11,\gi,On
      EndIf
    Else
      NPrint "LoadShape ",shn,",",\ig_file
      NPrint "Shapegadget ",gan,",",x,",",y,",",\ig_flags&1|\gf,",",\gi,",",shn
      If \ig_flags BitTst 15
        NPrint "Toggle ",gan,",",\gi,",On"
      EndIf
    EndIf
    ;
  End Select
Wend
repsh=0
Return

.maketext:txtf=-1:Use Window 1
;
.maketext2:lj=-1:lc1=-1:lc2=-1
;
ResetList gads()
While NextItem(gads())
  If \gt=4  ;window text?
    x=\gx-wlo:y=\gy-wto
    ;
    If txtf
      WLocate x,y
      WJam \wt_jam:WColour \wt_c1,\wt_c2
      Print \wt_text
    Else
      Print "WLocate ",x,",",y
      If \wt_jam<>lj
        lj=\wt_jam
        Print ":WJam ",lj
      EndIf
      If \wt_c1<>lc1 OR \wt_c2<>lc2
        lc1=\wt_c1:lc2=\wt_c2
        Print ":WColour ",lc1,",",lc2
      EndIf
      NPrint ""
      NPrint "Print ",Chr$(34),\wt_text,Chr$(34)
    EndIf
  EndIf
Wend
WJam 1:WColour 1,0
Return

.viewwin2
SizeLimits minw,minh,maxw,maxh
FlushEvents
Window 1,wx,wy,ww,wh,wf,wt$,wd,wb,11
inw=InnerWidth:inh=InnerHeight
wlo=WLeftOff:wto=WTopOff
SetMenu 0:Goto maketext


fw2:Free Window 2:FlushEvents
;
viewwin
Gosub viewwin2
Poke.l Peek.l(Addr Window(1))+62,0:Return

showabox2:x=SMouseX:y=SMouseY
.showabox
If x<xl Then x=xl
If y<yl Then y=yl
Box gx+wx,gy+wy,x,y,-1:Return

.shownew
Gosub makeglist:Gosub viewwin
;
.showwin
;
Box wx,wy,wx+ww-1,wy+wh-1,-1
Return

