;
; med test program
;

LoadMedModule 0,"lambada.med"     ;filename goes here

StartMedModule 0

SetInt 5
    PlayMed
End SetInt

MouseWait

For l=64 To 0 Step -1
  VWait
  SetMedVolume l
Next

End
