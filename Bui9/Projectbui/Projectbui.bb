; ; Project-BUI Written By Paul Burkey ; ; The R-Type/Project-X type scroll Demo for BUI ; ; Use Joystick to move up and down and press fire to end. ; Try removing the Vwait in the "main loop" to see how little time ; our scroll is really using. We have plenty of Frame time for our ; nasties to use........  �� : ��  #VIEW=336                ; This is slightly more than the "Viewable Width" #W=700                   ; Width of Map #H=18                    ; Height of Map  � map.w(#W-1,#H-1)     ; The array holding the Map data.  � 1,#VIEW*2,#H*16,5 ; We need two double width bitmaps for � 0,#VIEW*2,#H*16,5 ; use as a double buffer....  � grabshapes         ; Calls the Grab Shapes subroutine. � loadmap            ; Calls the load Map subroutine.  �7 50 : �4         ; Wait a while for I/O to end.  ;-- Set up a few variables ready for the game -->  db.b=1        ; The current bitmap to use (Double Buffer) mapx.w=0      ; The current possition in the game Map. xoff.w=4      ; The X Screen offset. yoff.w=31     ; The Y Screen offset. part.b=0      ; This tells the scroller what part of the scroll we are up to. paste.w=#VIEW ; The X possition to 'paste' the next column of graphics.  � 0,44,320,256,$fff8,5,8,32,#VIEW*2,#VIEW*2  ; 32 colour lowres slice. �, �	 0                                    ; Background Palette.   ;-- Main loop --> ; This will loop round until the Joy button is pressed. ; It's main job it to display the next bitmap in it's new possition. Then ; switch to the next bitmap and then it will call the scroll subroutine ; which will set up the next part of the scroll ready for the next frame. ;--------------->  tim=�� .main � �(1)=0    � db,xoff,yoff ; display current bitmap at current offset.    db=1-db           ; a good way of alternating between 0 and 1..    �, � db     ; use current buffer for output...    �7             ; Wait till the end of frame.. 50th second..    � _scroll     ; Setup the next Background frame.     yoff=�
(yoff+�(1),0,31) ; Temporary Up/Down Movement with joystick. �� �   ;-- This is the Scroll routine, it is called once every frame -->  ._scroll  xoff+1 ; next time we display the bitmap it will have moved left by 1 pixel.  � part    ; if the current status of "part" is 0,1,2,3,4 or 5 then we need to paste    ; some more of the current column in place on the right hand side (just    ; out of view)... I've split the job up into 6 parts so that the "graphics    ; work" is spread over a few frames...    ;    ; The folowing bit of code looks a bit long winded and it could be made    ; much shorter but this method of repeating the same command is faster    ; than any "shorter loop" could be. With games programming you should    ; ALWAYS use the faster method rather than the shorter looking.    ;    � 0      � map(mapx,0),paste,0  ; Whenever you can use "hard coded" numbers      � map(mapx,1),paste,16 ; instead of variables without making the      � map(mapx,2),paste,32 ; source code TOO BIG then you should use them.       ; only increment the "part" variable when we are on bitmap No.1 because      ; we need to do the same for each BitMap in the double buffer. the      ; folowing bit is a simple way of saying "If db=1 Then part+1"       part+db     � 1      � map(mapx,3),paste,48 ; We repeat the above process over and over      � map(mapx,4),paste,64 ; for the next few frames..      � map(mapx,5),paste,80      part+db    � 2      � map(mapx,6),paste,96      � map(mapx,7),paste,112      � map(mapx,8),paste,128      part+db    � 3      � map(mapx,9),paste,144      � map(mapx,10),paste,160      � map(mapx,11),paste,176      part+db    � 4      � map(mapx,12),paste,192      � map(mapx,13),paste,208      � map(mapx,14),paste,224      part+db    � 5      � map(mapx,15),paste,240      � map(mapx,16),paste,256      � map(mapx,17),paste,272 ; The last one!!      part+db    � 6      ; Now we copy the whole column from the right hand side which is out of      ; view, over to the left hand side (also out of view)  This could also      ; be spread over a few frames but copying a large Block is much faster      ; than copying lots of small ones..       � paste,0,16,288,paste-#VIEW,0      part+db    �      part+db      � part=8         ; When we've done the whole column and we've made an exact copy, just         ; off the left hand border.. We then reset the "part" variable and we         ; increment our 'x' possition in the Map Array and we move over ready         ; to work on our next column....          mapx+1    ; Next column in the Map Array         paste+16  ; Next 16 pixel possition in our bitmap.         part=0    ; Reset to the top of our column.          ; Now we check to see if we've run out of Bitmap. We only have         ; so much Bitmap to work on so if we have run out we need to Jump         ; back. This is okay because we have an exact copy of our current         ; display since we've been making second copy of each column.          � paste=#VIEW*2 ; are we out of Bitmap yet?            xoff-#VIEW    ; Move our offset back by 1 screen width.            paste-#VIEW   ; Move our column paste possition back also.         ��          � mapx>#W-25    ; This is a bit messy.. it just resets our map data                          ; to the start when we reach the end. Normaly we                          ; would end the level at this point....            mapx=0         ��      ��   � � �   .loadmap  ; This is just a basic load in a bank of data and peek in each item into an ; Array routine. The data file is very basic at the moment and it was created ; using my simple mapmaker program. The mapmaker program can be updated to ; include nastie possitions, movements etc....  �5 � 1,"map1.map" pos=60 � yy=0 � #H-1    � xx=0 � #W-1       map(xx,yy)=�.w (�(1)+pos)       pos+2    � xx � yy � 1 �   .grabshapes  ; Another very basic routine that loads an iff picture onto the ; current bitmap and Gets all the 16x16 shapes.  � 0,"background.iff",0 sh=0 � yy=0 � 15    � xx=0 � 19       � sh,xx*16,yy*16,16,16       sh+1    � � � ; We should clear the bitmap ready for the game itself... �  ; Thats all folks... See you next time...  