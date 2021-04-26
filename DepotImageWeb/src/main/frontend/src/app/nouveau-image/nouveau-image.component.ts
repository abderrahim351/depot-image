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
  ImageForm:FormGroup;
   publique1:string;
  constructor(private fb:FormBuilder, private service: ImageService) { }

  ngOnInit(): void {
    this.ImageForm = this.fb.group({
      titre: ['', [Validators.required, Validators.email]],
      soustitre: ['', Validators.required],
      description: ['', Validators.required],
      publique: ['', Validators.required],
    
    });
    this.publique();
  }
  ajouter(){
    this.service.ajouter({
      titre: this.ImageForm.get('titre').value,
      soustitre: this.ImageForm.get('soustitre').value,
      description: this.ImageForm.get('description').value,
      publique: this.ImageForm.get('publique').value,

    }).subscribe((data:any)=>{
      console.log(data);
    });
  }
  publique(){
    if (this.checked==true)
    this.publique1='publique';
    else{
      this.publique1='privé';
    }
  }
  
}
