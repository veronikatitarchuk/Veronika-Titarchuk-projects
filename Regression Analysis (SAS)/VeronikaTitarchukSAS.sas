libname final base "/home/u63145576/Final project";

Options validvarname=v7;

proc import datafile='/home/u63145576/Final project/world-happiness-report-2021.csv'
			DBMS=csv
			out=final.happiness
			replace;
			getnames=yes;
run;


data final.happiness(rename=('Ladder score'n= ladder_score 'Standard error of ladder score'n=std_ladder 
							'Logged GDP per capita'n = log_gdp_per_capita 'Social support'n = social_support 
							'Healthy life expectancy'n = healthy_life_expectancy 
							'Freedom to make life choices'n = freedom_life_choices 
							'Perceptions of corruption'n = percept_corruption));/*rename sas converted names to some convenient names*/
	set final.happiness;
run;

proc contents data=final.happiness;
run;

proc print data=final.happiness;
run;


*---------------------initial model-----------------------------;
proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption/influence press;
run;

proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption/vif ;
run;

proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption/selection=adjrsq mse aic bic cp;
run;

*----------------Initial model--------------------------;
*-------------Backward elimination---------------------------------;

proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption /selection=backward;
run;


*model after backward elimination;
proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity percept_corruption /influence press;
run;



*--------------Forward selection---------------;
proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption /selection=forward;
run;

proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							percept_corruption /influence press;
run;


*---------------------Stepwise----------------------------;
*full model;
proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption /selection=stepwise;
run;


proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							percept_corruption /vif;
run;


*---------------------Ten 10 best models-----------------------------------------------------------;

proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption /selection=cp best=10;
run;

proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							Generosity	percept_corruption /selection=adjrsq mse aic bic;
run;

proc reg data=final.happiness;
	model ladder_score =  log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							percept_corruption /influence press;
run;



*-----------------working with the chosen regressors----------------------------------;
proc reg data=final.happiness;
	model ladder_score = log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
							percept_corruption/vif;
run;


*-------------Check for constant variance------------------------------;
proc reg data=final.happiness plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = log_gdp_per_capita social_support healthy_life_expectancy
     						freedom_life_choices percept_corruption;
run;

proc reg data=final.happiness plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = log_gdp_per_capita;
run;

proc reg data=final.happiness plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = social_support;
run;

proc reg data=final.happiness plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = healthy_life_expectancy;
run;

proc reg data=final.happiness plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = freedom_life_choices;
run;

proc reg data=final.happiness plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = percept_corruption;
run;

*---------------Transformations----------------------;
data final.happiness3;
	set final.happiness;
	social_support_sqr = social_support**2;
	percept_corruption_sqr = (percept_corruption)**2;
	ladder_score_new = (ladder_score)**(1.5);
run;

*---------res vs pred after transformations----------;
proc reg data=final.happiness3 plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = percept_corruption_sqr;
run;

proc reg data=final.happiness3 plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = social_support social_support_sqr;
run;

proc reg data=final.happiness3 plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = log_gdp_per_capita social_support_sqr healthy_life_expectancy
     						freedom_life_choices percept_corruption;
run;

*to fix social we need to add social^2--------------------;
proc reg data=final.happiness3 plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score =  social_support social_support_sqr;
run;


proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							social_support social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;

*----------trying to normalize social----------------;
proc means data=final.happiness3 Mean StdDev ndec=3; 
   var social_support;
run;

proc stdize data=final.happiness3 out=normalized_data;
   var social_support percept_corruption;
run;

proc means data=normalized_data Mean StdDev ndec=3; 
   var social_support;
run;

proc reg data=normalized_data plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score =   social_support_sqr;
run;


proc reg data=normalized_data plots = residualbypredicted;
     ods select residualbypredicted;
     model ladder_score = percept_corruption_sqr;
run;

proc reg data=normalized_data;
	model ladder_score = log_gdp_per_capita
							social_support social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;

proc reg data=normalized_data;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;


proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;

proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=adjrsq mse aic bic cp;
run;

proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=adjrsq mse aic bic;
run;

proc transreg data=final.happiness3 test;
	model BoxCox(ladder_score) = identity(log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr);
run;

*-----------deleting social because of vif----------------------;
proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;

proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=adjrsq mse aic bic cp;
run;

proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=cp best=10;
run;

proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /influence press;
run;






proc transreg data=final.happiness3 test;
	model BoxCox(ladder_score) = identity(log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr);
run;

*----------press increased rapidly and didn't help, so leave response the same----;
proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /influence press;
run;

proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;

proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=adjrsq mse aic bic;
run;

proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=cp best=10;
run;

proc reg data=final.happiness3;
	model ladder_score_new = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /influence press;
run;







*-------------the best one without boxcox------------------------------;
*---------------------Final model----------------------;
proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /vif;
run;

proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /selection=adjrsq mse aic bic cp;
run;

proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /influence press;
run;

proc reg data=final.happiness3;
	model ladder_score = log_gdp_per_capita
							 social_support_sqr healthy_life_expectancy
							freedom_life_choices percept_corruption_sqr /r;
run;







*---------tried to normalize----------------------------;


proc stdize data=final.happiness3 out=normalized_data;
   var log_gdp_per_capita healthy_life_expectancy;
run;


proc reg data=normalized_data;
	model ladder_score = log_gdp_per_capita
							 social_support healthy_life_expectancy
							freedom_life_choices percept_corruption /vif;
run;



*------------Looking at the scatter and correlation matrix-----------;

proc sgscatter data=final.happiness;
	 matrix ladder_score log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
								percept_corruption generosity;
run;


proc sgscatter data=final.happiness2;
	 matrix ladder_score log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
								percept_corruption_sqr;
run;

proc corr data=final.happiness;
	var ladder_score log_gdp_per_capita 
							social_support	healthy_life_expectancy	freedom_life_choices 
								percept_corruption;
run;

data final.happinessAddingCross;
	set final.happiness;
	gdpHealtyLife = log_gdp_per_capita * healthy_life_expectancy;
	gdpSocial = log_gdp_per_capita*social_support;
	HealtyLifeSocial = healthy_life_expectancy*social_support;
	gdpLifeSocial = log_gdp_per_capita*healthy_life_expectancy*social_support;
run;

proc corr data=final.happinessAddingCross;
	var ladder_score  gdpHealtyLife gdpSocial HealtyLifeSocial gdpLifeSocial
						log_gdp_per_capita	social_support	healthy_life_expectancy	freedom_life_choices 
								percept_corruption;
run;

proc reg data=final.happinessAddingCross;
	model ladder_score =  gdpHealtyLife gdpSocial HealtyLifeSocial gdpLifeSocial
						log_gdp_per_capita	social_support	healthy_life_expectancy	freedom_life_choices 
								percept_corruption/vif;
run;


proc reg data=final.happinessAddingCross;
	model ladder_score =       gdpLifeSocial
									freedom_life_choices 
								percept_corruption/vif;
run;

proc reg data=final.happinessAddingCross;
	model ladder_score =       gdpLifeSocial
									freedom_life_choices 
								percept_corruption/selection=adjrsq mse aic bic cp;
run;

proc reg data=final.happinessAddingCross;
	model ladder_score =       gdpLifeSocial
									freedom_life_choices 
								percept_corruption/influence press;
run;


*------------problems with corruption-------------------;
proc sgscatter data=final.happinessAddingCross;
	 matrix ladder_score gdpLifeSocial
								freedom_life_choices 
								percept_corruption;
run;

proc stdize data=final.happinessAddingCross out=normalized_data;
   var  percept_corruption;
run;

proc sgscatter data=normalized_data;
	 matrix ladder_score gdpLifeSocial
									freedom_life_choices 
								percept_corruption;
run;

proc transreg data=final.happinessaddingcross test;
	model BoxCox(ladder_score)=identity(gdpLifeSocial
							freedom_life_choices 
							percept_corruption);
run;

data final.happinessTransfAfterCross;
	set final.happiness;
	gdpHealtyLife = log_gdp_per_capita * healthy_life_expectancy;
	gdpSocial = log_gdp_per_capita*social_support;
	HealtyLifeSocial = healthy_life_expectancy*social_support;
	gdpLifeSocial = log_gdp_per_capita*healthy_life_expectancy*social_support;
	percept_corruption_new = (percept_corruption);
	freedom_life_choices_new = log(freedom_life_choices);
	ladder_score_new = log(ladder_score);
run;

proc reg data=final.happinessTransfAfterCross;
	model ladder_score_new = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run;

proc reg data=final.happinessTransfAfterCross;
	model ladder_score_new = gdpLifeSocial
							freedom_life_choices 
							percept_corruption/influence press;
run;

proc reg data=final.happinessTransfAfterCross;
	model ladder_score_new = gdpLifeSocial
							freedom_life_choices 
							percept_corruption/selection=adjrsq mse aic bic cp;
run;

proc sgscatter data=final.happinessTransfAfterCross;
	 matrix ladder_score_new gdpLifeSocial
									freedom_life_choices_new 
								percept_corruption_new;
run;


proc reg data=final.happinessTransfAfterCross;
	model ladder_score_new = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run;


*---------outliers-------------------;
proc reg data=final.happinessTransfAfterCross;
model ladder_score_new = gdpLifeSocial
							freedom_life_choices 
							percept_corruption / stb
clb;
output out=stdres p= predict r = resid rstudent=r h=lev
cookd=cookd dffits=dffit;
run;

*---------CooksD outliers-------------------;
proc print data=stdres;
	where cookd>(4/149);
	var ladder_score_new gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run;

*----------DFFIT------------------;
proc print data=stdres;
	where dffit> abs(2*((4/149)**0.5));
	var ladder_score_new gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run;

*------------H hat------------------;
proc print data=stdres;
	where lev> 2*4/149;
	var ladder_score_new gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run;







*---------trying code to do weighted least squares----------------;

/* Weighted Least Squares as an Adjustment */
proc reg data=final.happinessTransfAfterCross;
 model ladder_score = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
 output out=WORK.PRED r=residual;
run;


data work.resid;
 set work.pred;
 absresid=abs(residual);
 sqresid=residual**2;
 
 
proc reg data=work.resid;
 model ladder_score = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
 output out=WORK.s_weights p=s_hat;
 model ladder_score = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
 output out=WORK.v_weights p=v_hat;
run;

** compute the weights using the estimated standard deviations**;
data work.s_weights;
set work.s_weights;
s_weight=1/(s_hat**2);
label s_weight = "weights using absolute residuals";

** compute the weights using the estimated variances**;
data work.v_weights;
set work.v_weights;
v_weight=1/v_hat;
label v_weight = "weights using squared residuals";

** Do the weighted least squares using the weights from the estimated
standard deviation**;
proc reg data=work.s_weights;
weight s_weight;
model ladder_score = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run;

proc reg data=work.v_weights;
weight v_weight;
model ladder_score = gdpLifeSocial
							freedom_life_choices 
							percept_corruption;
run; 







