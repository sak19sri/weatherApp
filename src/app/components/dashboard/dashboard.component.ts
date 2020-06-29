import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CITIES, FORECAST } from '../../constants/constant';
import { WeatherService } from '../../service/weather.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent implements OnInit {
  selectionForm: FormGroup;
  cities = CITIES;
  forcasts = FORECAST;
  formData: any;
  lat: number = 28.7;
  lng: number = 77.1;
  zoom: number = 3;
  currentTemp: number = 0;
  tableData: any;

  constructor(private fb: FormBuilder,
    public _weatherService: WeatherService) { }

  ngOnInit(): void {
    this.initialize();
    this.onchanges();
  }

  initialize(): void {
    this.selectionForm = this.fb.group({
      city: [this.cities[0]],
      forcast: [this.forcasts[0]]
    });
    this.loadData();
  }

  onchanges(): void {
    this.selectionForm.get('city').valueChanges.subscribe((res) => {
      this.loadData();
      this.currentTemp = 0;
    });
    this.selectionForm.get('forcast').valueChanges.subscribe((res) => {
      this.loadData();
      this.currentTemp = 0;
    });
  }

  loadData(): void {
    this.formData = {
      city: this.selectionForm.controls.city.value,
      forcast: this.selectionForm.controls.forcast.value
    }
    console.log('this.formData', this.formData);
  }

  markerClicked(event: any): void {

  }


  getTableData() {
    this._weatherService.getAnalyticsData().subscribe((res: any) => {
      if (res && res.length) {
        this.tableData = res;
      }
    }, (err) => {
      console.log('err in getting analytics data', err);
    });
  }

  submit(): void {
    console.log('this.formData', this.formData);
    this._weatherService.getWeatherData(this.formData).subscribe((res: any) => {
      console.log('response>>>>', res);
      this.currentTemp = res.current.temp;
      this._weatherService.save(this.formData).subscribe((res) => {
        console.log('successfully saved');
      });
    }, (err) => {
      console.log('err in getting weather data', err);
    });
  }

}
