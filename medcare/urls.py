"""medcare URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url,include

from django.contrib.staticfiles.urls import staticfiles_urlpatterns
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    url('^login/',include('login.url_login')),
    url('^staffreg/',include('staffreg.url_staffreg')),
    url('^patientreg/', include('patientreg.url_patientreg')),
    url('^dreg/', include('deprtreg.url_deprtreg')),
    url('^availdays/', include('availdays.url_availdays')),
    url('^treatment/',include('treatment.url_treatment')),
    url('^testreslt/',include('testreslt.url_testreslt')),
    url('^appoinment/',include('appoinment.url_appoinment')),
    url('^medicines/',include('medicines.url_medicines')),
    # url('^payments/', include('payments.url_payments')),
    url('^notify/', include('notify.url_notify')),
    #url('^record/', include('record.url_record')),
    url('^apppatients/', include('apppatients.url_apppatients')),
    url('^labtest/', include('labtest.url_labtest')),

]

urlpatterns+=staticfiles_urlpatterns()