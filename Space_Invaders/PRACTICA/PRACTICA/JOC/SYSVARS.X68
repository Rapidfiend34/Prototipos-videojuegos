; ==============================================================================
; SYSTEM VARIABLES
; ==============================================================================
            
; --- KEYBOARD RELATED VARIABLES -----------------------------------------------

KBDVAL      DS.B    1                       ; KEY STATE
KBDEDGE     DS.B    1                       ; KEY EDGE
FPS         DC.W    0                       ; FRAMES PER SECOND
FRAMES      DC.W    0

; --- INTRO STATE RELATED VARIABLES --------------------------------------------

ISCTIMER    DS.W    1                       ; INTRO TIMER

; --- TIMED INTERRUPT RELATED VARIABLES ----------------------------------------

SCRINTCT    DS.B    1                       ; INTERRUPT COUNTER
            DS.W    0
SCRINTCY    DS.B    1
            DS.W    0

*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
