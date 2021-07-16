import sys
from math import sqrt

from PIL import Image

primes = [
    2,
    3,
    5,
    7,
    11,
    13,
    13,
    17,
    19,
]

key = {
    63: '0',
    210: 'a',
    213: 'b',
    216: 'c',
    219: 'd',
    222: 'e',
    225: 'f',
    228: 'g',
    231: 'h',
    234: 'I',
    237: 'j',
    240: 'k',
    243: 'l',
    246: 'm',
    249: 'n',
    252: 'o',
    255: 'p',
    258: 'q',
    261: 'r',
    264: 's',
    267: 't',
    270: 'u',
    273: 'w',
    276: 'x',
    279: 'y',
    282: 'z',
    288: '{',
    294: '}',

}

alphabet = [
    '#',
    '-',
    '0',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z',
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'V',
    'W',
    'X',
    'Y',
    'Z',
    '{',
    '}',
]


def findindex(letter):
    for i in range(0, len(alphabet)):
        if alphabet[i] == letter:
            return i


# This function calculates the modular inverse of a number
def mod_inv(num, mod):
    for x in range(0, mod + 1):
        if (num * x) % mod == 1:
            return x
    sys.exit('[!!!] ERROR: modulo %d inverse of %d does not exists!' % (mod, num))


inputfile = "/home/andreas/Desktop/cybersec2021/08/decoded.txt"

with open(inputfile, "r") as data:
    undecoded = data.readline()

undecoded2 = undecoded.replace(" ", "")
byte_array = bytearray.fromhex(undecoded2)
parts = byte_array.decode().split(" ")
parts = parts[:-1]
# print(sqrt(len(parts)))
# print(parts)

# max = int(len(parts)/2)
# img = Image.new(mode="RGB", size=(int(len(parts)/2), int(len(parts)/2)))
#
# img.show()


# line = ""
# for p in parts:
#     p = int(p)
#     if p > 255:
#         line = line + "{:02d} ".format(1)
#         p = p % 255
#     line = line + "{:02d} ".format(p)
#     if len(line) > 80:
#         print(line)
#         line = ""
# print(line)

# line = ""
# for p in parts:
#     p = int(p)
#     # if p > 255:
#     #     line = line + "{:02d}".format(1)
#     #     p = p % 255
#     line = line + "{:02d}".format(p)
#     if len(line) > 80:
#         print(line)
#         line = ""
# print(line)


# line = ""
# for p in parts:
#     p = int(p) % 26
#     # if p > 255:
#     #     line = line + "{:02d}".format(1)
#     #     p = p % 255
#     line = line + "{:02d}".format(p)
#     if len(line) > 80:
#         print(line)
#         line = ""
# print(line)


# line = ""
# for p in parts:
#    p = int(p) % 26
#    # if p > 255:
#    #     line = line + "{:02d}".format(1)
#    #     p = p % 255
#    line = line + "{}".format(chr(p+ord('a')))
#    # if len(line) > 80:
#    #     print(line)
#    #     line = ""
# print(line)


# pos = 0
# with open("outfile", "wb") as of:
#     for p2 in parts:
#         convert = int(p2)
#         dat = bytes([convert])
#         of.write(dat)
#         pos += 1

print(len(alphabet))
print(findindex('a'))

#dat = ""
#with open("outfile", "w") as of:
#    #for p2 in parts:
#        #found = False
#        #for k in key.keys():
#            #if key[k] == int(p2):
#                dat = dat + k
#                found = True
#                break
#        #if not found:
#            #dat = dat + p2
#    #of.write(dat)
#

line = ""
for p in parts:
   idx = int(p) % 66
   # if p > 255:
   #     line = line + "{:02d}".format(1)
   #     p = p % 255
   line = line + alphabet[idx]
   # if len(line) > 80:
   #     print(line)
   #     line = ""
print(line)