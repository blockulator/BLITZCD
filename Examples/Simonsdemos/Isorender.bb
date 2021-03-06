;
; IsoRender.bb by Simon Armstrong
;

NEWTYPE .balltype ;all fields are .q (fast 32 bit fixed decimal point)
  depth.q  ;used for sorting list of balls from back to front
  col      ;color of ball (see table)
  x:y:z    ;3D position of ball
  sx:sy    ;screen position of ball after iso-projection
End NEWTYPE

Dim List ball.balltype(50)

LoadShapes 4,"balls.shapes" ;make sure we're in iso drawer (use CD)
LoadPalette 0,"balls.iff"   ;get palette from IFF

Screen 0,27,"ISOMETRIC SHAPE GENERATOR" ;open hires lace screen
Use Palette 0
ScreensBitMap 0,0

Box 15,15,15+33,15+41,1   ;shape must fit inside this frame
GetaShape 0,16,15,32,1    ;for clipping height

For r=0 To 15
  Restore myshape:f=16+r:Gosub makeframe:Blit f,r*40+16,104
  Restore mozzy:f=32+r:Gosub makeframe:Blit f,r*40+16,144
  Restore blade:f=48+r:Gosub makeframe:Blit f,r*40+16,184
Next

SaveShapes 16,f,"isoshapes" ;save all shapes generated
MouseWait
End

; the following routine reads the balls from data into a
; list, rotates them around the z axis, sorts, projects,
; draws, clips and finally grabs them by the tail!

.makeframe:
  Read n            ;how many balls follow?
  rsin=Sin(r*Pi/8)  ;needed for the 2D rotation that follows
  rcos=Cos(r*Pi/8)
  ClearList ball()  ;empty our work list of all items
  USEPATH ball()    ;save typing ball() 50 times!

  For b=0 To n-1
    AddItem ball()      ;read ball into the list array
    Read \col,tx,ty,\z
    \x=tx*rsin+ty*rcos  ;rotate it through a 2D matrix
    \y=tx*rcos-ty*rsin
    \depth=\x+\y        ;calculate depth for sorting
  Next

  Boxf 16,16,16+31,16+39,0   ;clear frame for drawing into
  SortList ball(),0          ;sort from back to front
  ResetList ball()           ;start with back ball
  While NextItem(ball())
    \sx=32+(\x-\y) ASL 4     ;calulate screen position
    \sy=48+(\x+\y-\z) ASL 3
    Blit \col,x+\sx,y+\sy    ;and draw to screen
  Wend

  ;the following clips the rendered shape so that smallest
  ;possible area is fetched for the destination shape

  y0=16:While BlitColl(0,16,y0)=0:Blit 0,16,y0:y0+1:Wend
  y1=16+39:While BlitColl(0,16,y1)=0:Blit 0,16,y1:y1-1:Wend
  GetaShape f,16,y0,32,y1-y0+1
  Handle f,16,36+16-y0
  Return

; shape data follows, feel free to experiment, just don't
; use numbers bigger than 2 for the xyz coordinates!
; format is
;   numballs
;     shape#,x,y,z
;     shape#,x,y,z etc.

.myshape:
  Data 14
  Data 8,-.2,-.3,0    ;feet
  Data 8,-.2,0.3,0
  Data 8,0.2,-.3,0
  Data 8,0.2,0.3,0
  Data 6,0,0,1        ;body
  Data 5,0,0,2.5      ;head
  Data 7,.4,-.2,2.5   ;eyes
  Data 7,.4,0.2,2.5
  Data 9,0,-.4,1      ;arms
  Data 9,0,0.4,1
  Data 9,.2,-.4,1
  Data 9,.2,0.4,1
  Data 9,.4,-.4,1
  Data 9,.4,0.4,1

.mozzy
  Data 5
  Data 4,0,0,1        ;body
  Data 7,-.4,0,1
  Data 7,-.6,0,1
  Data 8,.4,.2,1
  Data 8,.4,-.2,1

.blade
  Data 5
  Data 9,0,-.6,1.8
  Data 8,0,-.3,1.8
  Data 7,0,0,1.8
  Data 8,0,.3,1.8
  Data 9,0,.6,1.8

