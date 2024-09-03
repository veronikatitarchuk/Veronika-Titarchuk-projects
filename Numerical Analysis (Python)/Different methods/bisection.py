# -*- coding: utf-8 -*-
"""
Created on Tue Sep  5 10:03:28 2023

@author: User
"""
import numpy

def myBisection(f, a, b, TOL, N0):
    i = 1
    FA = f(a)
    while(i <= N0):   #do Steps 3-6
        p = a + (b-a)/2
        FP = f(p)
        if FP == 0 or (b-a)/2 < TOL:
            return p
        i+=1
        if numpy.sign(FA)*numpy.sign(FP)>0:
            a = p
            FA=FP
        else:
            b = p
    print("The method failed")
 
def f(x):
    return x**3+2*x**2+10*x-20
 
a = 1
b = 2
TOL = 1E-10
N0 = 100000  
 
p = myBisection(f, a, b, TOL, N0)
print('p = ', p)
        