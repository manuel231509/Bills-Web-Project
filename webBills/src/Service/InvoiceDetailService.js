import axios from 'axios';

export default class InvoiceDetailService {
    url = "http://localhost:8080/api/invoicedetail/";

    getAll() {
        return axios.get(this.url + "all");
    }
    save(invoicedetail) {
        return axios.post(this.url + "save", invoicedetail);
    }

    update(invoicedetail) {
        return axios.post(this.url + "update", invoicedetail);
    }

    findInvoiceId(inv_id){
        return axios.get(this.url + "findinvoiceid/"+inv_id);
    }

    delete(invdet_id){
        return axios.get(this.url + "delete/"+ invdet_id);
    }
}