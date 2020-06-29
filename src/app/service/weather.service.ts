import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { APP, URL } from '../constants/constant';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  fiveDaysInMiliSeconds = 1593251673;

  constructor(public http: HttpClient) { }

  getWeatherData(data: any) {
    console.log('data in service', data);
    const current_url = `${URL.BASE_URL}?lat=${data.city.latitude}&lon=${data.city.longitude}&units=metric&
    exclude=hourly,daily&appid=${APP.APPID}`;
    const history_url = `${URL.BASE_URL}/timemachine?lat=${data.city.latitude}&lon=${data.city.longitude}&dt=${this.fiveDaysInMiliSeconds}&appid=${APP.APPID}`;

    const url = data.forcast.value === 'current' ? current_url : history_url;
    return this.http.get(url);
  }

  getPreviousFiveDays() {
    console.log((Date.now() - this.fiveDaysInMiliSeconds));
    return (Date.now() - this.fiveDaysInMiliSeconds);
  }

  save(data: any) {
    const url = `${URL.BACKEND_URL}/weatherstatus/save`;
    const req = {
      cityName: data.city.name,
      typeOfEnquiry: data.forcast.value
    };
    return this.http.post(url, req);
  }

  getAnalyticsData() {
    const url = `${URL.BACKEND_URL}/weatherstatus/getAnalytics`;
    return this.http.get(url);
  }

}
