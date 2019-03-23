; ; IsoBlaster V0.1 by Simon Armstrong ; ; You have to run Isorender first to create the shapes  ; ; set up all the main types and arrays ;  � .object  ;same is IsoRender.bb but with extra fields   depth.q:id     ;depth and shape id 0=me 1=mozzy   x:y:z          ;3D coordinates of shape   vx:vy          ;velocity in both x and y   rot:rot2:rotv  ;rotation variables   sx:sy          ;screen position after an iso-projection � �  � �� bob.object(50)  �3 .object *me  � qsin(255),qcos(255)  ;set up array of sin/cos values so � r=0 � 255           ;we don't have to call sys ones in game   qsin(r)=�
(��*r/128):qcos(r)=�(��*r/128) �  � map.w(20,20) ;bit smaller than the dungeon (at present)  ; ; load all the graphics from disk ;  �
 0,"blocks.shapes"  ;tiles for the ground �
 4,"balls.shapes"   ;balls for fx etc. � 0,"balls.iff"     ;palette for forground playfield � 0,"blocks.iff",8  ;palette for backrground �
 16,"isoshapes"     ;shapes rendered by IsoRender.bb  � 0,320+64,256+80,3  ;foreground A � 1,320+64,256+80,3  ;foreground B � 2,640,512,3        ;large scrolling background  � 0,32:� 1,32     ;two queues for double buffered blitting  � p 320+(`1-`2)�D 4,128+(`1+`2-`3)�D3:� �       ;back � f 320+32-sx+(`1-`2)�D4,128+32-sy+(`1+`2-`3)�D3:� �  ;front  ; ; create Blitz Mode display which for this game is ; dual playfield with double buffered foreground! ;  �4 � 0,44,320,256,$fffa,6,8,32,320+64,640 �, �	 0  � setupmap  ;place a few blocks in the 2D array � drawmap   ;draw map onto large background playfield � initgame  ;initialise objects in the game  ; then of course the standard main loop of any game...  � �(0)=0   �7:� db,32,40,sx:� 2,sx,sy,32  ;position bitmaps in display   db=1-db:�, � db     ;swap buffer for double buffered drawing   � moveme              ;move me   � movethem            ;and them   � db:� drawbobs ;then draw everyone in new pos ;  MOVE#-1,$dff180   ;view frame time! ��  �  ; ; and now what makes everything work properly! ;  .moveme:   �� *me                     ;use pointer to my object in list   \rot=�(\rot-�(1)/2,0,16) ;rotate according to joystick   � �(1)=1                    ;if fire     \vx+qsin(\rot �D 4) �E 6    ;then thrust in direction I     \vy+qcos(\rot �D 4) �E 6    ;am pointing (my \rot)   ��   \vx-\vx �E 5          ;this subtracts a fraction of my velocity   \vy-\vy �E 5          ;off my velocity (same as drag)   \x=�
(\x+\vx,0,19) ;this adds celocity to my position   \y=�
(\y+\vy,0,19)   \sx=!p{\x,\y,\z}         ;calculate screen coordinates   sx=�
(\sx-160,0,320) ;calulate scroll values for display so   sy=�
(\sy-128,0,256) ;I am as close to center as possible   \sx-sx+32:\sy-sy+32   \depth=\x+\y             ;don't forget my depth variable   � map(\x,\y)=1          ;oh and colour in block if I stand on one     �, � 2:x=�(\x):y=�(\y):� 2,!p{x,y,0}:�, � db   ��   �  .movethem:   �� bob()           ;simple routine that loops through   � bob()         ;all shapes makeing /id=2 shapes   � �(bob())   ;fly round in circles...     � \id=2       \rot=�(\rot+\rotv,0,16)       \rot2=�(\rot2+1,0,16)       \x+qsin(\rot �D 4) �E 4:\y+qcos(\rot �D 4) �E 4       \sx=!f{\x,\y,\z}       \depth=\x+\y     ��   ��  .drawbobs:   � bob(),0        ;sort from back to front   � bob()         ;loop through and draw, if they   �� bob()           ;are \id=2 draw propellor as well   � �(bob())     � �	(\sx,\sy,1,1,16,40,320+31,256+40)       � db,\id*16+\rot,\sx,\sy       � \id=2 �! � db,\id*16+16+\rot2,\sx,\sy     ��   ��   �  .initgame:   � bob()   � bob():*me.object=bob()   *me\id=1,.5,.5,0   � i=0 � 2     � �(bob())       bob()\id=2,�(19),�(19):\rotv=(�(1)-.5)�E 2     ��   �   �  .setupmap:   � x=0 � 19:� y=0 � 19:map(x,y)=1:�:�   � x=9 � 11:� y=9 � 11:map(x,y)=-1:�:�   �  .drawmap:   �, � 2   � x=0 � 19     � y=0 � 19       � map(x,y)>-1 �! � map(x,y),!p{x,y,0}     �   �   �  