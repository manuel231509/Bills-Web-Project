import axios from 'axios';

export default class InvoiceService{
    url ="http://localhost:8080/api/invoice/";
    
    getAll(){
        return axios.get(this.url + "all");
    }
    save(invoice){
        return axios.post(this.url + "save",invoice);
    }

    update(invoice){
        return axios.post(this.url+"update",invoice);
    }

    delete(inv_id){
        return axios.get(this.url + "delete/"+ inv_id);
    }
}