# -*- coding: utf-8 -*-
"""
Created on Wed Sep 20 19:52:14 2023

@author: User
"""
import math

def mySecant(f, p0, p1, TOL, N0):
    i = 2
    q0 = f(p0)
    q1 = f(p1)
    while i <= N0:
        p = p1 - q1*(p1-p0)/(q1-q0)
        if abs(p-p1) < TOL:
            return p
        i += 1
        p0 = p1
        q0 = q1
        p1 = p
        q1 = f(p)
    print('Method failed.')
    
def f(x):
    return x**3+2*x**2+10*x-20

p = mySecant(f, 1, 2, 1E-10, 100000)
print('p = ', p)