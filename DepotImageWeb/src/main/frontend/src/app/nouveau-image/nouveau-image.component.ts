import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ImageService } from './image.service';

@Component({
  selector: 'app-nouveau-image',
  templateUrl: './nouveau-image.component.html',
  styleUrls: ['./nouveau-image.component.scss']
})
export class NouveauImageComponent implements OnInit {
  checked: boolean = false;
  imageForm:FormGroup;
   publique1:string;

   uploadedFiles: any[] = [];

  constructor(private fb:FormBuilder, private service: ImageService) { }

  ngOnInit(): void {
    this.imageForm = this.fb.group({
      titre: [null, [Validators.required, Validators.email]],
      soustitre: [null, Validators.required],
      description: [null, Validators.required],
      publique: [false, Validators.required],

    });
    this.publique();
  }
  ajouter(){
    this.service.ajouter({
      titre: this.imageForm.get('titre').value,
      soustitre: this.imageForm.get('soustitre').value,
      description: this.imageForm.get('description').value,
      publique: this.imageForm.get('publique').value,

    }).subscribe((data:any)=>{
      console.log(data);

      //upload image

    });
  }
  publique(){
    if (this.checked==true)
    this.publique1='publique';
    else{
      this.publique1='privé';
    }
  }

  onUpload(event) {
    console.log('on upload!!');

}

}
