import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  ajouter : boolean=false;
  constructor() { }

  ngOnInit(): void {
  }
  ajouter_utl(){
    this.ajouter=true;
  }

}
