;
; workbench clock program
;

WBStartup:FindScreen 0   ;open window on workbench

Window 0,100,20,160,104,$100E,"BLITZ CLOCK",1,2

WEllipse 80,50,70,35,1   ;draw clock

NEWTYPE .xy:x.w:y.w:End NEWTYPE ;coordinates for hands
Dim h.xy(59)
For i=0 To 59
  h(i)\x=68*Sin(i*Pi/30),-32*Cos(i*Pi/30)
Next

Repeat
  Wline 80,50,80+h(Secs)\x,50+h(Secs)\y,0
  Wline 80,50,80+h(Mins)\x,50+h(Mins)\y,0
  Wline 80,50,80+h(hr)\x/2,50+h(hr)\y/2,0
  WLocate 40,80:NPrint Date$(SystemDate)
  hr=5*(Hours MOD 12)
  Wline 80,50,80+h(hr)\x/2,50+h(hr)\y/2,1
  Wline 80,50,80+h(Mins)\x,50+h(Mins)\y,1
  Wline 80,50,80+h(Secs)\x,50+h(Secs)\y,2
  Delay_ 50    ;goto sleep for 1 second
Until Event=$200
End
