
; "normal" distribution with Mean and Standard Deviation
Function.q normal{mean,std}
  SHARED switch.w,u1,u2
  switch=NOT switch
  If switch Then Function Return u1*Cos(u2)+mean
  u1=std*Sqr(-2*Log(Rnd(1)) )
  u2=Rnd(2*Pi)
  Function Return u1*Sin(u2)+mean
End Function

Screen 0,4
ScreensBitMap 0,0
Dim size(320)

Repeat
  x=normal{160,30}
  If x>0 AND x<320
    Plot x,size(x),1
    size(x)+1
  EndIf
Until Joyb(0)<>0
End
