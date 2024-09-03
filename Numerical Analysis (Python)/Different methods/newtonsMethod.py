# -*- coding: utf-8 -*-
"""
Created on Wed Sep 20 19:42:16 2023

@author: User
"""
import math

def MyNewtonsMethod(f, fp, p0, TOL, N0):
    i = 1
    while i <= N0:
        p = p0 - f(p0)/fp(p0)
        if abs(p-p0) < TOL:
            return p
        i += 1
        p0 = p
    print("Method failed.")
    
def f(x):
    return x**3+2*x**2+10*x-20

def fp(x):
    return 3*x**2+4*x+10

p = MyNewtonsMethod(f, fp, 1.5, 1E-10, 100000)
print('p = ', p)