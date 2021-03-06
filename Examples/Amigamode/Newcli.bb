;
; launching a CLI from a Blitz2 application
;
; note the use of the new commands WBWidth and WBHeight for full size cli
;
; also note choice chr$(10)+"ENDCLI" at the end of cmd$ in diskcopy example
; to automatically close CLI after doing a command
;

MenuTitle 0,0,"PROJECT"
MenuItem 0,0,0,0,"CLI           ","Z"
MenuItem 0,0,0,1,"DISKCOPY      "
MenuItem 0,0,0,2,"QUIT          ","Q"

Screen 0,10
Window 0,0,10,640,DispHeight-10,$1000,"NEWCLI DEMO (via menu)",1,2

SetMenu 0

Repeat
  ev.l=WaitEvent
  If ev=256 AND MenuHit=0
    Select ItemHit
      Case 0:cmd$="":Gosub docmd
      Case 1:cmd$="DISKCOPY DF0: TO DF0:"+Chr$(10)+"ENDCLI":Gosub docmd
      Case 2:End
    End Select
  EndIf
Forever

docmd:
  cli$="con:0/0/"+Str$(WBWidth)+"/"+Str$(WBHeight)+"/MY OWN APPLICATIONS CLI"
  If WriteFile(0,cli$)
    WBenchToFront_
    Execute_ &cmd$,Peek.l(Addr File(0)),0
    WBenchToBack_
    CloseFile 0
  EndIf
  Return

