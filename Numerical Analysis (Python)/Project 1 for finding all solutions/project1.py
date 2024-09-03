# -*- coding: utf-8 -*-
"""
Created on Mon Oct  9 19:02:00 2023

@author: User
"""
import numpy as np

def MyRoots(MyMuellers, MyHorners, MySynth, MyNewtonsMethod, a, p0):
    f = a
    TOL = 1E-5
    n0 = 1000
    realRoots = []
    complexRoots = []
    i = 0
    while(len(f)>2):
        if MyHorners(f, p0)[1] == 0:
            break;
        cv = MyNewtonsMethod(f, MyHorners, p0, TOL, n0)
        if(isinstance(cv, float) == False):
            break;
        realRoots.append(cv)
        f = MySynth(f, realRoots[i])
        i += 1
    
    i = 0
    while(len(f)>2):
        cv = MyMuellers(p0, f, MyHorners, TOL, n0)
        if(isinstance(cv, complex) == False):
            break;
        complexRoots.append(cv)
        f = MySynth(f, complexRoots[i])
        i += 1
    
    if (isinstance(cv, complex) == False):
        realRoots.append(-1*f[1]/f[0])
    else:
        complexRoots.append(-1*f[1]/f[0])
    return realRoots, complexRoots
        
       
def MyMuellers(p0, f, MyHorners, TOL, N):
    p1 = p0 + 1
    p2 = p0 + 2
    h1 = p1-p0
    h2 = p2-p1
    g1 = (MyHorners(f, p1)[0] - MyHorners(f, p0)[0])/h1
    g2 = (MyHorners(f, p2)[0] - MyHorners(f, p1)[0])/h2
    d = (g2 - g1)/(h2 + h1)
    i = 3
    while (i <= N):
        b = g2 + h2 * d
        e = (b**2 - 4 * MyHorners(f, p2)[0] * d)**(1/2)
        if (abs(b - e) < abs(b + e)):
            ee = b + e
        else:
            ee = b - e
        h = (-2 * MyHorners(f, p2)[0])/ee
        p = p2 + h
        if (abs(h) < TOL):
            return p
        p0 = p1
        p1 = p2
        p2 = p
        h1 = p1 - p0
        h2 = p2 - p1
        g1 = (MyHorners(f, p1)[0] - MyHorners(f, p0)[0])/h1
        g2 = (MyHorners(f, p2)[0] - MyHorners(f, p1)[0])/h2
        d = (g2 - g1)/(h2 + h1)
        i = i+1
    return("Method Failed")

def MyHorners(a, x):
    n = len(a)-1
    y = z = a[0]
    for i in range(1, n):
        y = x * y + a[i]
        z = x * z + y
    y = x*y+a[n]
    return(y, z)

def MySynth(a, r):
    n = len(a)
    if (isinstance(r, complex) == True):
        b = np.zeros(n - 1, dtype = complex)
    else:
        b = np.zeros(n - 1)
    i  = 1
    b[0] = a[0]
    while (i < n-1):
        b[i] = a[i] + r * b[i - 1]
        i = i+1
    return b

def MyNewtonsMethod(a, MyHorners, p0, TOL, N0):
    i = 1
    while i <= N0:
        if MyHorners(a, p0)[1] == 0:
            return("Method failed.")
        p = p0 - MyHorners(a, p0)[0]/MyHorners(a, p0)[1]
        if abs(p - p0) < TOL:
            return p
        i += 1
        p0 = p
    return("Method failed.")

c = MyRoots(MyMuellers, MyHorners, MySynth, MyNewtonsMethod, [1, 1, 1], 0)
print("Real Roots: ", c[0])
print("Complex Roots: ", c[1])

a = MyRoots(MyMuellers, MyHorners, MySynth, MyNewtonsMethod, [1, 0, -1], 1)
print("Real Roots: ", a[0])
print("Complex Roots: ", a[1])

d = MyRoots(MyMuellers, MyHorners, MySynth, MyNewtonsMethod, [1, 0, 1], 0)
print("Real Roots: ", d[0])
print("Complex Roots: ", d[1])

e = MyRoots(MyMuellers, MyHorners, MySynth, MyNewtonsMethod, [1, 2, 1], 1)
print("Real Roots: ", e[0])
print("Complex Roots: ", e[1])

f = MyRoots(MyMuellers, MyHorners, MySynth, MyNewtonsMethod, [6, -1, -198, 256, 672], 1)
print("Real Roots: ", f[0])
print("Complex Roots: ", f[1])