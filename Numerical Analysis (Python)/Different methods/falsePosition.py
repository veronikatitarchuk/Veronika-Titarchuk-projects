# -*- coding: utf-8 -*-
"""
Created on Mon Sep 11 10:10:40 2023

@author: User
"""

import numpy as np

def myFalsePosition(f, p0, p1, TOL, N0):
    i = 2
    q0 = f(p0)
    q1 = f(p1)
    while i <= N0:
        p = p1 - q1*(p1 - p0)/(q1 - q0)
        if abs(p - p1)< TOL:
            return p
        i = i+1
        q = f(p)
        if np.sign(q)*np.sign(q1) < 0:
            p0 = p1
            q0 = q1
        p1 = p
        q1 = q
    print("Method failed.")
    
def f(x):
    return x**3+2*x**2+10*x-20
    
p0 = 1
p1 = 2
TOL = 1E-10
N0 = 100000
p = myFalsePosition(f, p0, p1, TOL, N0)
print('p = ', p)
