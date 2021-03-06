;
; memory available demo
;

  #ANY=0
  #CHIP=2
  #FAST=4
  #CLEAR=$10000    ;NA only used when allocating memory
  #LARGEST=$20000
  #TOTAL=$80000

  NPrint " fast largest:",AvailMem_(#FAST|#LARGEST)
  NPrint " fast maximum:",AvailMem_(#FAST|#TOTAL)
  NPrint "   fast avail:",AvailMem_(#FAST)
  NPrint ""
  NPrint " chip largest:",AvailMem_(#CHIP|#LARGEST)
  NPrint " chip maximum:",AvailMem_(#CHIP|#TOTAL)
  NPrint "   chip avail:",AvailMem_(#CHIP)
  NPrint ""
  NPrint "  all largest:",AvailMem_(#ANY|#LARGEST)
  NPrint "  all maximum:",AvailMem_(#ANY|#TOTAL)
  NPrint "    all avail:",AvailMem_(#ANY)

  MouseWait ;(needed if running from Blitz2 and not CLI)
  End
