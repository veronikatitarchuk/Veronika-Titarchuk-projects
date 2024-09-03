# -*- coding: utf-8 -*-
"""
Created on Wed Nov  8 18:31:30 2023

@author: User
"""
import numpy as np

def MyCompositeSimpson(f, a, b, n):             
    h = (b-a)/n
    xn =  np.linspace(a, b, n)  
    yn = np.zeros(n)  
    for i in range(n):
        if xn[i]==0:
            yn[i] = 0
        else:
            yn[i] = f(xn[i])                      
    sumo = 0         
    sume = 0         
    for i in range(1, n, 2):             
        sumo += yn[i]         
    for i in  range(2, n, 2):             
        sume += yn[i]         
    s = (h/3)*(yn[0] + sumo*4 + sume*2 + yn[n-1])
    return s

def MyCompositeSimpson1(f, a, b, n):             
    h = (b-a)/n
    xn =  np.linspace(a, b, n)  
    yn = np.zeros(n)  
    for i in range(n):
        yn[i] = f(xn[i])                      
    sumo = 0         
    sume = 0         
    for i in range(1, n, 2):             
        sumo += yn[i]         
    for i in  range(2, n, 2):             
        sume += yn[i]         
    s = (h/3)*(yn[0] + sumo*4 + sume*2 + yn[n-1])
    return s

'Taylor Series'
def f1(x):
    return x**(-0.5)-x**(0.5)+0.5*x**(1.5)-1/(6*x**2.5)+1/(24*x**3.5)

'Second integral with Taylor Series'
def f2(x):
    return np.exp(-x)/(x**(0.5))-(x**(-0.5)-x**(0.5)+0.5*x**(1.5)-(1/6)*x**2.5+(1/24)*x**3.5)

def f3(x):
    return np.exp(-1/x)*x**(-1.5)

def f4(x):
    return np.exp(-x)*x**0.5

def f5(x):
    return np.exp(-1/x)*x**(-2.5)


first= 2-2/3+1/5-1/21+1/108+MyCompositeSimpson(f2, 0, 1, 20)
second = MyCompositeSimpson(f3, 0, 1, 20)
third = first+second
first2 = MyCompositeSimpson1(f4, 0, 1, 20)
second2 = MyCompositeSimpson(f5, 0, 1, 20)
third2 = first2+second2

print("a) Composite Simpson's first integral': ", first)
print("b) Composite Simpson's' second integral: ", second)
print("c) gamma(0.5) = ", third)
print("d) Composite Simpson's' first integral: ", first2)
print("e) Composite Simpson's' second integral: ", second2)
print("f) gamma(1.5): ", third2)

