"""
This will parse data from the food_pyramid_data.txt file which contains caloric nutritional information of common foods.
Key components are extracted and printed for each of these common foods.
TO RUN (PYTHON 2.7):
cat food_pyramid_data.txt | python project4parser.py
"""

tokens = ('LEADINGCELL','CELL','TRAILINGCELL', )
literals = ['.',  ]

# Tokens
#t_COLUMNS = r'^Food_.*$'
t_LEADINGCELL = r'^[^\t]*\t'
t_CELL = r'[^\t]*\t'
t_TRAILINGCELL = r'[^\t]+$'


# Ignored characters
t_ignore = ' \r'

def t_newline(t):
    r'\n+'
    t.lexer.lineno += t.value.count("\n")

def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)

# Build the lexer
import ply.lex as lex   # ply.lex comes from the ply folder in the PLY download.
lexer = lex.lex()

# Parsing rules

global time_step
time_step = 0


def p_start(t):
    '''start : LEADINGCELL CELL CELL CELL CELL trash CELL CELL CELL CELL CELL TRAILINGCELL
    '''
    if ("Food_Code" not in str(t[1])):
        s = str(t[4]) + str(t[5]) + str(t[2]) + ' has ' + str(t[11]) + 'total calories'
        s = s.replace('\t', ' ')
        comp = str(t[7]) + ' calories from oils, ' + str(t[8]) + ' calories from solid fats, ' + str(t[9]) + ' calories from added sugars, ' + str(t[10]) + ' calories from alcohol, ' + str(t[12] + ' calories from saturated fats')
        print s.replace('\t', ' ')
        print comp.replace('\t', ' ') + '\n'

    # for i in range (1,len(t)):
    #     print t[i]
    #     print '------------'
    # print '***FINISHED START****'


def p_trash(t):
    'trash : CELL CELL CELL CELL CELL CELL CELL CELL CELL CELL CELL CELL CELL CELL CELL'
    s = ''
    for i in range(1,len(t)):
        s = s + str(t[i])
    t[0] = s



def p_error(t):
    if t == None:
        print("Syntax error at '%s'" % t)
    else:
        print("Syntax error at '%s'" % t.value)

import ply.yacc as yacc   # ply.yacc comes from the ply folder in the PLY download.
parser = yacc.yacc()

while True:
    try:
        s = raw_input('')
    except EOFError:
        break
    parser.parse(s)


