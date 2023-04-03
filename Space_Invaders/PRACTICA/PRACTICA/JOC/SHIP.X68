; ==============================================================================
; SHIP MANAGEMENT
; ==============================================================================
            
; ------------------------------------------------------------------------------
           
SHIPINIT
; INITIALIZE SHIP.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVE.W  #SCRHEIGH,(SHIPPOSY)
            MOVE.W  #SCRWIDTH/2,(SHIPPOSX)
            RTS
            
; ------------------------------------------------------------------------------
SHIPUPD
; UPDATES THE SHIP.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            
            MOVEM.L  D0-D1,-(A7)
            
            ; UPDATE Y COORDINATE
            MOVE.W  (SHIPPOSY),D0
            BTST.B  #1,(KBDVAL)
            BEQ     .CHKDWN
            SUB.W   #SHIPSPED,D0
.CHKDWN     BTST.B  #3,(KBDVAL)
            BEQ     .CHKLEFT
            ADD.W   #SHIPSPED,D0
            ; UPDATE X COORDINATE
.CHKLEFT    
            MOVE.W  (SHIPPOSX),D1
            BTST.B  #0,(KBDVAL)
            BEQ    .CHKRIGH
            SUB.W   #SHIPSPED,D1
.CHKRIGH    BTST.B  #2,(KBDVAL)
            BEQ     .CONT
            ADD.W   #SHIPSPED,D1
            BRA     .CONT
     
            ; CHECK BORDER COLLISIONS
.CONT       CMP.W   #SHIPHEIG/2,D0
            BGE     .CONT2
            MOVE.W  #SHIPHEIG/2,D0
            BRA     .CONT3
.CONT2      CMP.W   #SCRHEIGH-SHIPHEIG/2-20,D0
            BLE     .CONT3
            MOVE.W  #SCRHEIGH-SHIPHEIG/2-20,D0
           

            BRA     .CONT3
.CONT3      CMP     #SCRWIDTH-SHIPWIDT-SHIPWIDT/3,D1
            BLE     .CONT4
            MOVE.W  #SCRWIDTH-SHIPWIDT-SHIPWIDT/3,D1

            BRA     .DONE
.CONT4      CMP.W   #SHIPWIDT+SHIPWIDT/3,D1
            BGE     .DONE
            MOVE.W  #SHIPWIDT+SHIPWIDT/3,D1

            BRA     .DONE            

            ; UPDATE VARIABLE
.DONE       MOVE.W  D0,(SHIPPOSY)
            MOVE.W  D1,(SHIPPOSX)

            ; CHECK MARCIANOS COLLISIONS
            MOVE.W  #2,D0
            JSR     DMMFRSTO
.LOOP       CMP.L   #0,A0
            BEQ     .CHKMARGD
            ADD.L   #8,A0
            
            ; CHECK HORIZONTAL COLLISION
.CHKHORZ    MOVE.W  (SHIPPOSX),D1
            SUB.W   #SHIPWIDT/2,D1
            MOVE.W  (A0),D2
            ADD.W   #MARWIDTH/2,D2
            CMP.W   D1,D2
            BLT     .CHKMOT
            ADD.W   #SHIPWIDT,D1
            SUB.W   #MARWIDTH,D2
            CMP.W   D1,D2
            BGT     .CHKMOT
            MOVE.W  (SHIPPOSY),D1
            ADD.W   #SHIPHEIG/2+SHIPHEIG/4,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/2,D2
            CMP.W   D1,D2
            BGT     .CHKMOT
            SUB.W   #SHIPHEIG+SHIPHEIG/4,D1
            ADD.W   #MARHEIGH,D2
            CMP.W   D1,D2
            BLT     .CHKMOT
            ;DELETE MARCIANO
            JSR     AGLKILL
            MOVE.W  #1,D7
            TRAP    #2
            SUBQ.W  #1,(MAXMAR)
           
            ; MODIFY LIVE BAR
            ADD.W   #SCRWIDTH/5,(LENGLIFE)   
            SUBQ.W  #1,(LIFES)
            CMP.W   #0,(SCOOUT)
            BEQ     .NEXT

            ;CHECK COLLISION IN THE ROCKETS
.CHKMOT     MOVE.W  (SHIPPOSX),D2
            SUB.W   #SHIPWIDT+SHIPWIDT/2,D2
            MOVE.W  (A0),D3
            ADD.W   #MARWIDTH/2,D3
            CMP.W   D2,D3
            BLT     .CHKMOTRI
            MOVE.W  (SHIPPOSX),D2
            SUB.W   #SHIPWIDT/2,D2
            CMP.W   D2,D3
            BGT     .CHKMOTRI
            MOVE.W  2(A0),D3
            ADD.W   #MARHEIGH/2,D3
            MOVE.W  (SHIPPOSY),D2
            CMP.W   D2,D3
            BLT     .CHKMOTRI
            SUB.W   #MARHEIGH,D3
            ADD.W   #SHIPHEIG,D2
            SUB.W   #SHIPHEIG/3,D2
            CMP.W   D2,D3
            BGT     .CHKMOTRI
            ;DELETE MARCIANO
            JSR     AGLKILL
            MOVE.W  #1,D7
            TRAP    #2
            SUBQ.W  #1,(MAXMAR)
            ; MODIFY LIVE BAR
            ADD.W   #SCRWIDTH/5,(LENGLIFE)   
            SUBQ.W  #1,(LIFES)
            CMP.W   #0,(SCOOUT)
            BEQ     .CHKMOTRI

            ;CHECK NEXT ROCKET
.CHKMOTRI   MOVE.W  (SHIPPOSX),D2
            ADD.W   #SHIPWIDT+SHIPWIDT/2,D2
            MOVE.W  (A0),D3
            SUB.W   #MARWIDTH/2,D3
            CMP.W   D2,D3
            BGT     .NEXT
            MOVE.W  (SHIPPOSX),D2
            ADD.W   #SHIPWIDT/2,D2
            CMP.W   D2,D3
            BLT     .NEXT
            MOVE.W  2(A0),D3
            ADD.W   #MARHEIGH/2,D3
            MOVE.W  (SHIPPOSY),D2
            CMP.W   D2,D3
            BLT     .NEXT
            SUB.W   #MARHEIGH,D3
            ADD.W   #SHIPHEIG,D2
            SUB.W   #SHIPHEIG/3,D2
            CMP.W   D2,D3
            BGT     .NEXT
            ;DELETE MARCIANO
            JSR     AGLKILL
            MOVE.W  #1,D7
            TRAP    #2
            SUBQ.W  #1,(MAXMAR)

            ; MODIFY LIVE BAR
            ADD.W   #SCRWIDTH/5,(LENGLIFE)   
            SUBQ.W  #1,(LIFES)
            CMP.W   #0,(SCOOUT)
            BEQ     .NEXT

            ; MODIFY POINTS
            SUB.W   #POINTS,(SCOOUT)
.NEXT       JSR     DMMNEXTO
            CMP.W   #2,D0
            BNE     .GUIDELOOP
            BRA     .LOOP
            
.CHKMARGD            MOVE.W  #4,D0
            JSR     DMMFRSTO
.GUIDELOOP  CMP.L   #0,A0
            BEQ     .EXIT
            ADD.L   #8,A0
            BRA     .CHKHORZ

.EXIT       MOVEM.L  (A7)+,D0-D1

            RTS
            
; ------------------------------------------------------------------------------
SHIPPLOT
; PLOT THE SHIP.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.L D0-D4/D1-D2,-(A7)
; NUCLEUS RECTANGLE_______________________

            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #NUCPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #NUCFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (SHIPPOSX),D1
            SUB.W   #SHIPWIDT/2,D1
            MOVE.W  (SHIPPOSY),D2
            SUB.W   #SHIPHEIG/2,D2
            MOVE.W  (SHIPPOSX),D3
            ADD.W   #SHIPWIDT/2,D3
            MOVE.W  D2,D4
            ADD.W   #SHIPHEIG,D4

            ; DRAW NUCLEUS RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15

            
; LEFT RECTANGLE__________________________

            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #MOTPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #MOTFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (SHIPPOSX),D1
            SUB.W   #SHIPWIDT/2+2,D1
            MOVE.W  (SHIPPOSY),D2
            MOVE.W  (SHIPPOSX),D3
            SUB.W   #SHIPWIDT+SHIPWIDT/3,D3
            MOVE.W  D2,D4
            ADD.W   #SHIPHEIG,D4
            SUB.W   #SHIPHEIG/3,D4
            
            ; DRAW LEFT RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15



; RIGHT RECTANGLE_________________________
            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #MOTPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #MOTFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (SHIPPOSX),D1
            ADD.W   #SHIPWIDT/2+2,D1
            MOVE.W  (SHIPPOSY),D2
            MOVE.W  (SHIPPOSX),D3
            ADD.W   #SHIPWIDT+SHIPWIDT/3,D3
            MOVE.W  D2,D4
            ADD.W   #SHIPHEIG,D4
            SUB.W   #SHIPHEIG/3,D4
            
            ; DRAW RIGHT RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15
            
            
            
            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #$00FFFFF0,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #$0000FF00,D1
            TRAP    #15
            ; DEFINE COORDINATES
            MOVE.W  (LIFES),D0
            CMP.W   #0,D0
            BLE     .DEAD
            MOVE.W  #0,D0
            ADD.W   (LENGLIFE),D0
            MOVE.W  D0,D1
            ADD.W   #20,D1
            MOVE.W  #SCRHEIGH,D2
            SUB.W   #10,D2
            MOVE.W  #SCRWIDTH,D0
            SUB.W   (LENGLIFE),D0
            MOVE.W  D0,D3
            SUB.W   #20,D3
            MOVE.W  #SCRHEIGH,D4
            SUB.W   #23,D4
            
            
            ; DRAW LIFE BAR
            MOVE.B  #87,D0
            TRAP    #15


.DEAD       MOVEM.L (A7)+,D0-D4/D1-D2

            RTS
            
            MOVEM.L (A7)+,D1-D2/D0-D3
            RTS



*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
