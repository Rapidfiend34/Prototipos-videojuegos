; ==============================================================================
; SYSTEM CONSTANTS
; ==============================================================================

; --- SCREEN RELATED CONSTANTS -------------------------------------------------

SCRWIDTH    EQU     640                    ; SCREEN WIDTH
SCRHEIGH    EQU     640                    ; SCREEN HEIGHT

; --- KEYCODES -----------------------------------------------------------------

KBDLEFT     EQU     $25
KBDUP       EQU     $26
KBDRIGHT    EQU     $27
KBDDOWN     EQU     $28
KBDFIRE1    EQU     'X'
KBDFIRE2    EQU     ' '
KBDFIRE3    EQU     $10
KBDPAUSE    EQU     'P'

; --- FRAMES PER SECOND (FPS) --------------------------------------------------
SCRFPS      EQU     50                      ; DEFINES THE MAXIMUM FPS
SCRINTN     EQU     1   
FPSINTNM    EQU     2                       ; FRAMES UPDATE TRAP NUMBER 

; --- TRAP NUMBERS -------------------------------------------------------------

SCRUPTN     EQU     0                       ; SCREEN UPDATE TRAP NUMBER
KBDUPTN     EQU     1                       ; KEYBOARD UPDATE TRAP NUMBER
SNDPLTN     EQU     2                       ; SOUND PLAY TRAP NUMBER

*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
