; RED Map Editor V2.5 ; ; ARexx Demo Program 1 ; ; Requires: ;           REXXMAST to be running ;           RED Map Editor V2.5 to be running ;           OS2+ ;  ;Define some variables �3.l   port                  ; our arexx port �3.w   button                ; button the user selected �3.s   r �3.s   str  ��  ;Check for map editor running... � ��("MapEditor")=0   �� "Could not locate Map Editor ARexx port!"   � ��  ;Create a message port first. port=("TestProg1") � port=0   �� "Failed to open ARexx port!"   � ��  ;Set our default port for commands to go to  "MapEditor"  ;Bring Map Editors screen to the front...  "ACTIVATE"  ;Requester... r=("REQUEST 'Demo Program 1|By Stephen McNamara|R.W.E. 1996|Do you wish to run this demo?' '_Yes|_No'") button=�(r) � button=0    "REQUEST 'Okay then, I will just go away...' '_Thank you'"   � ��  ;How about a load file requester... r=("LOADREQUEST 'Select any old file...' 'Blitz2:'") � r<>""    "REQUEST 'You selected file "+r+"'" ��  ;Shape Selector r=("SHAPEREQUEST 'Select a shape' CANCEL 0") � r<>"-1"    "REQUEST 'You selected shape number "+r+"'" �"    "REQUEST 'You pressed Cancel you scum :)'" ��  ;Lock up  "LOCK"  "REQUEST 'The Map Editor is currently locked - you cannot select any gadgets or menus at the moment!'"  "UNLOCK"   "REQUEST 'Thats the end...  For now...'" �  