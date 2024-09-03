# -*- coding: utf-8 -*-
"""
Created on Wed Sep  6 15:11:34 2023

@author: User
"""
import math

def myFixedPoint(f, p0, TOL, N0):
    i=1
    while i <= N0:
        p = f(p0)
        if abs(p-p0)<TOL:
            return p
        i+=1
        p0=p
    print("The method failed")
    
def f(n):
    return 1+0.5*math.sin(n)

p = myFixedPoint(f, 1, 1E-6, 10000)
print('p = ', p)