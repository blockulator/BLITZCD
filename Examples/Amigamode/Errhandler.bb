NEWTYPE .ezrequest
  size.l:flags.l:title$:mess$:gad$
End NEWTYPE

DEFTYPE .ezrequest errz

SetErr
  PutReg d0,err.l
;  errz\size=20,0,"RUNTIME ERROR",Peek$(err),"QUIT|CONT"
;  If AutoRequest_
  err$=Mki$(40)+Chr$(12)+Peek$(err)+Mki$(0)
  DisplayAlert_ 0,&err$,20:End
End SetErr

Dim a(10)
a(11)=12

