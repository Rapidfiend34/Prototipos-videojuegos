; ==============================================================================
; MARGUID MANAGEMENT (AGENT)
; SUMARGUI  (W)      (A0)
; MAGUIHIG  (W)     2(A0)
; GUISPEED  (W)     4(A0)
; ==============================================================================
            
; ------------------------------------------------------------------------------
GUIDEINT
; INITIALIZE MARGUID.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L  D0/A0,-(A7)
            MOVE.W  (A4),(A0)
            MOVE.W  2(A4),2(A0)
            MOVE.W  #GUISPEED,4(A0)
            MOVEM.L (A7)+,D0/A0
            RTS
            
; ------------------------------------------------------------------------------

GUIDEIPD
; UPDATES THE MARGUID.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.W D0-D2/A0-A1,-(A7)
            ; UPDATE MARCIANOS COORDINATES
            ;CHECK MARGUIDE ABOUT SHIP POSITION Y
            MOVE.W  (SHIPPOSY),D0
            MOVE.W  2(A0),D1
            CMP.W   D0,D1
            BEQ     .GUIDEX
            MOVE.W  (SHIPPOSY),D0
            SUBQ.W  #1,D0
            MOVE.W  2(A0),D1
            CMP.W   D0,D1
            BLT     .FALLGUIDE
            MOVE.W  (SHIPPOSY),D0
            ADDQ.W  #1,D0
            MOVE.W  2(A0),D1
            CMP.W   D0,D1
            BLT     .GUIDEX
            ;MOVEMENT UP
            MOVE.W  2(A0),D0 
            SUB.W   4(A0),D0
            MOVE.W  D0,2(A0)
            BRA     .GUIDEX
            ;MOVEMENT DOWN
.FALLGUIDE  
            MOVE.W  2(A0),D0 
            ADD.W   4(A0),D0
            MOVE.W  D0,2(A0)
            ;COMPARE POSITION X BETWEEN MARGUIDE AND SHIP     
.GUIDEX     MOVE.W  (SHIPPOSX),D0
            MOVE.W  (A0),D1
            CMP.W   D0,D1
            BEQ     .DONE
            MOVE.W  (SHIPPOSX),D0
            SUBQ.W  #1,D0
            MOVE.W  (A0),D1
            CMP.W   D0,D1
            BLT     .RTGUIDE
            MOVE.W  (SHIPPOSX),D0
            ADDQ.W  #1,D0
            MOVE.W  (A0),D1
            CMP.W   D0,D1
            BLT     .DONE
            ;MOVEMENT TO THE LEFT
            MOVE.W  (A0),D0 
            SUB.W   4(A0),D0
            MOVE.W  D0,(A0)
            BRA     .DONE 
            ;MOVEMENT TO THE RIGHT   
.RTGUIDE    MOVE.W  (A0),D0 
            ADD.W   4(A0),D0
            MOVE.W  D0,(A0)    
.DONE
       MOVEM.W (A7)+,D0-D2/A0-A1

            RTS
            
; ------------------------------------------------------------------------------
GUIDEPLT
; PLOT THE MARGUID.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0-D4,-(A7)

; CIRCLE__________________________________

            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #GCIRPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #GCIRFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            SUB.W   #MARWIDTH/4,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/4,D2
            MOVE.W  (A0),D3
            ADD.W   #MARWIDTH/4,D3
            MOVE.W  2(A0),D4
            ADD.W   #MARHEIGH/4,D4
            
            ; DRAW CIRCLE       
            MOVE.B  #88,D0
            TRAP    #15
            
; LEFT RECTANGLE__________________________
            
            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #GRECPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #GRECFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            SUB.W   #MARWIDTH/2,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/2,D2
            MOVE.W  (A0),D3
            SUB.W   #MARWIDTH/3,D3
            MOVE.W  2(A0),D4
            ADD.W   #MARHEIGH/2,D4
            
            ; DRAW LEFT RECTANGLE
            
            MOVE.B  #87,D0
            TRAP    #15
            
; RIGHT RECTANGLE_________________________

            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #GRECPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #GRECFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            ADD.W   #MARWIDTH/3,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/2,D2
            MOVE.W  (A0),D3
            ADD.W   #MARWIDTH/2,D3
            MOVE.W  2(A0),D4
            ADD.W   #MARHEIGH/2,D4
            
            ; DRAW RIGHT RECTANGLE
            
            MOVE.B  #87,D0
            TRAP    #15


            
            MOVEM.L (A7)+,D0-D4

            RTS



*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
