;This program is used to test variable tracing with the LES Debugger ;When it stops the debugger will in invoked.  You should then use the ;STEP gadget on the debugger screen to single step through this program. ;Variables will be added to the debuggers trace list by this prog, and the ;debuggers variable trace window will be automatically opened.  ;Constants used for output modes #nocare=0 #dec=1 #hex=2 #bin=3 #nolen=1 #showlen=2  ;Our variables that we want traced (DEFINED BEFORE AddVarTrace is CALLED!) a.b=50 b.q=45.42 c.s="Leading Edge!"  ;Stop the program and let the debugger take over �-  ;Open the window ��  ;Add our variables �� a,"a (counter)",#hex  ; Add variable a (output mode=hexadecimal) �� b,"b",#dec            ; Add variable b (output mode=decimal) �� c,"c",#showlen        ; Add string c (output mode=show len/maxlen)  �  