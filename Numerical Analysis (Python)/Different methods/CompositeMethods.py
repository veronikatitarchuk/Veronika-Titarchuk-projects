# -*- coding: utf-8 -*-
"""
Created on Wed Nov  1 17:32:23 2023

@author: User
"""

import numpy as np 

def MyCompositeMidpoint(f, a, b, n):         
    h = (b-a)/n 
    xn =  np.linspace(a, b, n)  
    yn = np.zeros(n)
    for i in range(n):
        yn[i] = f(xn[i])
    summ = 0     
    for i in range(1, n, 2):         
        summ +=yn[i]     
    m = 2 * h * summ    
    return m 

def MyCompositeTrapezoid(f, a, b, n): 
    h = (b-a)/n
    xn =  np.linspace(a, b, n)  
    yn = np.zeros(n) 
    for i in range(n):
        yn[i] = f(xn[i])       
    summ = 0     
    for i in range(1, n):        
        summ += yn[i]     
    t = (h/2) * (yn[0] + 2*summ + yn[n-1])         
    return t 

def MyCompositeSimpson(f, a, b, n):             
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

def Romberg(f, a, b, k):
    R = np.zeros((k,k))
    for i in range(k):
        R[i,0] = MyCompositeTrapezoid(f, a, b, 2**i)
    for j in range(1,k):
        for i in range(j,k):
            R[i,j] = R[i,j-1] + (1/(4**(j)-1))*(R[i,j-1]-R[i-1,j-1])
    return R

def Gauss2(f, a, b):
    y = ((b-a)/2) * (f((((b-a)/2)*.57737)+((a+b)/2)) + f((((b-a)/2)*-.57737)+((a+b)/2)))
    return y

def Gauss3(f, a, b):
    y = ((b-a)/2) * ((8/9)*f((((b-a)/2)*0)+((a+b)/2)) + (5/9)*f((((b-a)/2)*.7746)+((a+b)/2))\
                     + (5/9)*f((((b-a)/2)*-.7746)+((a+b)/2)))
    return y

def Gauss4(f,a,b):
    y = ((b-a)/2) * (.652145*f((((b-a)/2)*.339981)+((a+b)/2)) + .652145*f((((b-a)/2)*-.339981)+((a+b)/2))\
                     + (.347855)*f((((b-a)/2)*.861136)+((a+b)/2)) + (.347855)*f((((b-a)/2)*-.861136)+((a+b)/2)))
    return y


def f(x):
    return np.exp(-1*x**2)

l = 2/np.sqrt(np.pi)

cm = MyCompositeMidpoint(f, 0, 0.5, 128)
ct = MyCompositeTrapezoid(f, 0, 0.5, 128)
cs = MyCompositeSimpson(f, 0, 0.5, 128)
rmb = Romberg(f, 0, 0.5, 8)[7,0]
g2 = Gauss2(f, 0, 0.5)
g3 = Gauss3(f, 0, 0.5)
g4 = Gauss4(f, 0, 0.5)
print("Composite Midpoint estimate: ", l*cm) 
print("Composite Trapezoid estimate: ", l*ct)
print("Composite Simpson's' estimate: ", l*cs)
print("Romberg estimate: ", l*rmb)
print("Gaussian n=2: ", l*g2)
print("Gaussian n=3: ", l*g3)
print("Gaussian n=4: ", l*g4)