import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-image-details',
  templateUrl: './image-details.component.html',
  styleUrls: ['./image-details.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ImageDetailsComponent implements OnInit {

  idDoc:number;

  constructor(    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (this.route.snapshot.paramMap.get('id')) {
      this.idDoc = Number(this.route.snapshot.paramMap.get('id'));

      // recupertaion des details du doc et l'afficher

    }else{
      this.idDoc = null;
    }

  }

}
