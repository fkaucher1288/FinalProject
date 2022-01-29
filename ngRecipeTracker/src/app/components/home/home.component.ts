import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap'
import { NgForm } from '@angular/forms';

interface Credentials {
  username:string
  password:string
  passwordc:string
  registering:boolean
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @ViewChild('loginModalTemplate', { read: TemplateRef })
  loginModalTemplate!: TemplateRef<any>;

  creds: Credentials = {
    username: "",
    password: "",
    passwordc:"",
    registering:false
  }

  constructor(private authService: AuthService, private modal: NgbModal) {}

  ngOnInit() {}

  onClickLogin() {
    this.modal.open(this.loginModalTemplate).result.then(
    (reason)=>{
      console.log("close reason:", reason);

      if (reason === 'login') {
        this.authService.login(this.creds.username, this.creds.password).subscribe({
          next: (result) => {
            console.log("login", result);
          },
          error: (error) => {
            console.log("login error", error);
          }
        })
      } else if (reason === 'register') {
        if (this.creds.password === this.creds.passwordc) {
          this.authService.register({
            username: this.creds.username,
            password: this.creds.password
          }).subscribe({
            next: (result) => {
              console.log("register", result);
            },
            error: (error) => {
              console.log("register error", error);
            }
          })
        } else {
          alert("Please make sure both passwords match.");
        }
      }
    }, (rejected)=>{
      console.log("rejected", rejected);
    });
  }

  userLogInCheck() {
    return false;
    // return this.authService.getCredentials();
  }

  onUserRegisterClick(modal:NgbModalRef) {
    if (this.creds.registering) {
      modal.close('register');
    }

    this.creds.registering=true;
  }
}
