Poke.b $bfe301,0 ;255

While Joyb(0)=0
  Print Right$(Bin$(Peek.b($bfe101)),8),"  "
  mark1.w=Peek.b($bfe101)
  mark2.w=Peek.b($bfd000)
  Print Right$(Bin$(Peek.b($bfd000)),8),"  "

  jox=(mark1&4 - (mark1&8) ASR 1)ASR 2 + 1
  joy=mark1&1 - (mark1&2) ASR 1 + (mark2&4) ASR 2
  Print " ",jox,joy," "

  jox=(mark1&64 - (mark1&128) ASR 1)ASR 6 + 1
  joy=(mark1&16 - (mark1&32) ASR 1)ASR 4 + (mark2&1)

  Print " ",jox,joy," "

  Print " ",Joyx(1)+1,Joyy(1)+1," "

  NPrint ""
Wend

End

;sorry a bit of creative license above..
;joystick 3
;  jox=(mark1&4 - (mark1&8) ASR 1)ASR 2 + 1
;  joy=mark1&1 - (mark1&2) ASR 1
;  fire= (mark2&4) ASR 2
;
;joystick 4
;  jox=(mark1&64 - (mark1&128) ASR 1)ASR 6 + 1
;  joy=(mark1&16 - (mark1&32) ASR 1)ASR 4
;  fire= (mark2&1)

