SetErr
  PutReg d0,err.l:err$=Mki$(40)+Chr$(12)+Peek$(err)+Mki$(0)
  DisplayAlert_ 0,&err$,20:End
End SetErr

Dim a(10)
a(11)=12

