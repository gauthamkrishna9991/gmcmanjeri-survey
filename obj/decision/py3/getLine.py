import linecache
def printColor(x):
    if x[0] == 'G':
        print( '\33[32m', 'LOW RISK','\33[0m',sep='')
    if x[0] == 'Y':
        print( '\33[93m', 'MEDIUM RISK' ,'\33[0m',sep='')
    if x[0] == 'O':
        print( '\33[33m', 'MEDIUM-HIGH RISK', '\33[0m',sep='')
    if x[0] == 'R':
        print( '\33[31m', 'VERY HIGH RISK' ,'\33[0m',sep='')
    if x[0] == 'D':
        print( '\33[41m', 'DANGER ZONE','\33[0m',sep='')
    return

def calculate(diabetes, gender, smoker, age, sbp, cholesterol, file):
    pos = 0
    if diabetes == False:
        pos += 320
    if gender:
        pos += 160
    if smoker:
        pos += 80
    pos += ((70-(age-(age%10)))*2)
    pos += int((180 -(sbp - (sbp%20)))/4)
    pos += (cholesterol - 3)
    try:
        return linecache.getline(file,pos)
    except FileNotFoundError:
        print("FILE NOT FOUND")
        return None

print("DIABETES (Y/N):", end='')
db,gn,sm,ag,sbp,chl = False,False,False,70,180,4
sw = input()
if sw == 'Y' or sw == 'y':
    db = True
else:
    db = False
print("GENDER (M/F):",end='')
sw = input()
if sw == 'M' or sw == 'm':
    gn = False
else:
    gn = True
print("SMOKER (Y/N):",end='')
sw = input()
if sw == 'Y' or sw == 'y':
    sm = True
else:
    sm = False
print("AGE :", end='')
sw = int(input())
if sw < 40:
    ag = 40
elif sw > 79:
    ag = 70
else:
    ag = sw
print("SBP :",end='')
sw = int(input())
if sw > 180:
    sbp = 180
elif sw < 120:
    sbp = 120
else:
    sbp = sw
print("CHOLESTEROL :",end='')
sw = int(input())
if sw > 8:
    chl = 8
elif sw < 4:
    chl = 4
else:
    chl = sw
printColor(calculate(db, gn, sm, ag, sbp, chl,'./data.txt'))