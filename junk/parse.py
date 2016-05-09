tokens = ('HEAD', 'TITLE', 'YEAR', 'INTEGER', 'FREQUENCY','UNIT')
literals = ['.', ':' ]

# Tokens
t_HEAD  = r'^Series.*$'
t_TITLE   = r'.*Annual\t'
t_YEAR = r'^d{4}$'
t_FREQUENCY = r'\tA\t'
t_UNIT = r'\tDollars.*$'

# Ignored characters
t_ignore = " \r"

def t_INTEGER(t):
    r'\d+'
    try:
        t.value = int(t.value)
    except ValueError:
        print("Integer value too large %d", t.value)
        t.value = 0
    return t

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
    '''start : HEAD
             | empty
             | data
             | float
    '''
    # print ("Saw: ", t[1])


def p_data(t):
    'data : TITLE INTEGER FREQUENCY float UNIT'
    print("Year, Price($/bbl): " + str(t[2]) + ", " + str(t[4]))

def p_float(t):
    'float : INTEGER "." INTEGER'
    t[0] = str(t[1]) + str(t[2]) + str(t[3])

def p_empty(t):
    'empty : '
    pass

def p_error(t):
    if t == None:
        print("Syntax error at '%s'" % t)
    else:
        print("Syntax error at '%s'" % t.value)

import ply.yacc as yacc   # ply.yacc comes from the ply folder in the PLY download.
parser = yacc.yacc()

while True:
    try:
        s = input('')
    except EOFError:
        break
    parser.parse(s)

# To run the parser do the following in a terminal window: cat data.txt | python parse.py"
