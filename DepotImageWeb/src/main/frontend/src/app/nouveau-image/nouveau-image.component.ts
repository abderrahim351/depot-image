import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
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
   f1:File;
   uploadedFiles: any[] = [];
  b:boolean=false;   
  formdata1;
  
   

  constructor(private fb:FormBuilder, private service: ImageService,private messageService: MessageService ) { }

  ngOnInit(): void {
    this.imageForm = this.fb.group({
      titre: ["", [Validators.required, Validators.email]],
      sousTitre: ["", Validators.required],
      description: ["", Validators.required],
      publique: [false, Validators.required],
      

    });
    this.publiquefn();
  }

  ajouter(){
    console.log(this.imageForm.get('publique').value);
    this.service.ajouter({
      titre: this.imageForm.get('titre').value,
      sousTitre: this.imageForm.get('sousTitre').value,
      description: this.imageForm.get('description').value,
      publique: this.imageForm.get('publique').value,
      

    }).subscribe((data:any)=>{
      console.log(data);
      
      
      console.log("document ajouter");
      this.service.uploadimg(this.formdata1).subscribe((data:any)=>{
        console.log("image ajouter");
      })
      
      
      
     

    });
  }
  publiquefn(){
    if (this.checked==true)
    this.publique1='publique';
    else{
      this.publique1='privé';
    }
  }

  onUpload(files) {
    

    let image = files.item(0);

    console.log(files)
    
    // console.log("test1");
    // console.log(event.target.files[0]);
    // console.log(event.target.files[0].type);
    let formData = new FormData();
  formData.append('file',image);
    
      this.formdata1=new FormData();
      this.formdata1=formData;
     
    

  
}

}
