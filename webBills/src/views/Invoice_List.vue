<template>
  <div>
    <br />
    <v-container class="elevation-20">
      <v-row dense>
        <v-col :cols="12">
          <v-card>
            <div class="text-right">
              <v-btn color="secondary" @click="getAll" medium>
                <v-icon medium color="darken-2"> autorenew </v-icon>
              </v-btn>
            </div>
            <v-card-title
              style="text-transform: uppercase"
              class="justify-center"
            >
              Invoice listing
            </v-card-title>
            <v-spacer></v-spacer>
            <v-container>
              <v-data-table
                :headers="headers"
                :items="desserts"
                :items-per-page="5"
                class="elevation-3"
              >
                <template v-slot:item="row">
                  <tr>
                    <td>
                      {{ row.item.inv_id }}
                    </td>
                    <td>
                      {{ row.item.inv_nameclient }}
                    </td>
                    <td>
                      {{ row.item.inv_date }}
                    </td>
                    <td>
                      {{ row.item.inv_subtotal }}
                    </td>
                    <td>
                      {{ row.item.inv_vat }}
                    </td>
                    <td>
                      {{ row.item.inv_total }}
                    </td>
                    <td>
                      <v-btn
                        color="secondary"
                        @click="viewDetail(row.index)"
                        fab
                        small
                      >
                        <v-icon small color="darken-2"> fa fa-eye </v-icon>
                      </v-btn>
                    </td>
                    <td>
                      <v-btn color="accent" @click="edit(row.index)" fab small>
                        <v-icon small color="darken-2"> fa fa-edit </v-icon>
                      </v-btn>
                    </td>
                    <td>
                      <v-btn
                        color="error"
                        @click="onDelete(row.index)"
                        fab
                        small
                      >
                        <v-icon small color="darken-2"> mdi-delete </v-icon>
                      </v-btn>
                    </td>
                  </tr>
                </template>
              </v-data-table>
            </v-container>
            <br />
          </v-card>
        </v-col>

        <v-dialog
          transition="dialog-bottom-transition"
          v-model="dialog"
          width="100%"
          height="100%"
        >
          <v-card>
            <v-toolbar class="vToolbar" color="accent" dark>
              <v-icon large color="white"> fa fa-edit </v-icon>
              Update Invoice
              <div class="close">
                <v-btn
                  icon
                  :disabled="!valueBoolClose"
                  color="error"
                  @click="close"
                  x-large
                >
                  <v-icon color="error" medium> fa fa-window-close </v-icon>
                </v-btn>
              </div>
            </v-toolbar>
            <v-card-text>
              <UpdateInvoice
                :invoice="invoice"
                :desserts="invoiceDetailData"
                @updateInvoice="emit"
                @updateValueBoolClose="emitUpdateValueBoolClose"
              />
            </v-card-text>
          </v-card>
        </v-dialog>

        <v-col :cols="12">
          <v-card>
            <v-card-title
              style="text-transform: uppercase"
              class="justify-center"
            >
              Detailed list of the invoice
            </v-card-title>
            <v-spacer></v-spacer>
            <v-container>
              <v-data-table
                :headers="headers_detail"
                :items="desserts_detail"
                :items-per-page="5"
                class="elevation-3"
              >
                <template v-slot:item="row">
                  <tr>
                    <td>
                      {{ row.item.invdet_itemid }}
                    </td>
                    <td>
                      {{ row.item.invdet_itemname }}
                    </td>
                    <td>
                      {{ row.item.invdet_quantityitem }}
                    </td>
                    <td>
                      {{ row.item.invdet_itemvalue }}
                    </td>
                  </tr>
                </template>
              </v-data-table>
            </v-container>
            <br />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import InvoiceService from "../Service/InvoiceService";
import InvoiceDetailService from "../Service/InvoiceDetailService";
import UpdateInvoice from "../views/Update_Invoice";
import moment from "moment";
export default {
  name: "Invoice_List",
  components: {
    UpdateInvoice,
  },
  data: () => ({
    dialog: false,
    addMessageBoolInv: false,
    addMessageBoolInvSuccess: false,
    valueBoolClose: true,
    invoiceDetailData: [],
    // ------------ Invoice form validations ------------
    validInvoice: true,
    invoice: {
      inv_id: "",
      inv_nameclient: null,
      inv_date: Date,
      inv_subtotal: "0",
      inv_vat: "1",
      inv_total: "0",
    },
    invoiceRules: {
      inv_idRules: [
        (v) => !!v || "Bill number is required",
        (v) => v > 0 || "The item id must be greater than 0",
        (v) =>
          (v && v.length >= 5) ||
          "The bill number must be at least 5 characters long",
      ],

      inv_nameclientRules: [
        (v) => !!v || "Client name is required",
        (v) =>
          (v && v.length <= 50) ||
          "Client name must be less than 10 characters",
      ],

      inv_dateRules: [
        (v) => !!v || "Date is required",
        (v) => (v && v.length <= 50) || "Date must be less than 10 characters",
      ],
      sumSubTotal: 0,
      inv_subtotalRules: [
        (v) => !!v || "Subtotal is required",
        (v) =>
          v != 0 || "You must add one or more items to confirm the invoice",
      ],

      inv_vatRules: [
        (v) => !!v || "VAT is required",
        (v) => v > 0 || "The VAT value must be greater than 0",
        (v) => (v && v <= 100) || "The VAT range is 100%",
      ],
      inv_totalRules: [
        (v) => !!v || "Total is required",
        (v) =>
          v != 0 || "You must add one or more items to confirm the invoice",
      ],
    },

    headers: [
      {
        text: "Invoice ID",
        align: "start",
        sortable: false,
        value: "invoiceID",
      },
      { text: "Name Client", value: "nameClient" },
      { text: "Invoice Date", value: "invoiceDate" },
      { text: "Subtotal", value: "subTotal" },
      { text: "VAT", value: "VAT" },
      { text: "Total", value: "total" },
      { text: "View Detail", value: "viewDetail" },
      { text: "Modify", value: "modify" },
      { text: "Remove", value: "removeItem" },
    ],
    desserts: [],
    // ------------ END ------------

    // ------------ Detail form validations ------------
    validDetail: true,
    invoiceDetail: {
      invdet_id: null,
      invdet_inv_id: null,
      invdet_itemid: "",
      invdet_itemname: "",
      invdet_quantityitem: "",
      invdet_itemvalue: "",
    },
    invoiceDetailRules: {
      invdet_itemidRules: [
        (v) => !!v || "Id article is required",
        (v) => v > 0 || "The item id must be greater than 0",
        (v) =>
          (v && v.length >= 5) ||
          "The id article must be at least 5 characters long",
      ],
      invdet_itemnameRules: [
        (v) => !!v || "Name article is required",
        (v) =>
          (v && v.length <= 50) ||
          "Name article must be less than 10 characters",
      ],
      invdet_quantityitemRules: [
        (v) => !!v || "Quantity items is required",
        (v) => v >= 1 || "Quantity items must be greater than or equal to 1",
        (v) => v <= 50 || "The maximum number of articles is 50",
      ],
      invdet_itemvalueRules: [
        (v) => !!v || "Item value is required",
        (v) => v > 0 || "The item value must be greater than 0",
        (v) => v >= 50 || "The value of the item must be greater than 50 pesos",
      ],
    },

    headers_detail: [
      {
        text: "Item ID",
        align: "start",
        sortable: false,
        value: "itemId",
      },
      { text: "Item Name", value: "itemName" },
      { text: "Quantity Items", value: "quantityItems" },
      { text: "Item Value", value: "itemValue" },
    ],
    desserts_detail: [],
    // ------------ END ------------
  }),
  invoiceService: null,
  invoiceDetailService: null,
  created() {
    this.invoiceService = new InvoiceService();
    this.invoiceDetailService = new InvoiceDetailService();
  },
  mounted() {
    this.getAll();
  },
  methods: {
    close() {
      this.dialog = false;
      this.getAll();
    },
    emitUpdateValueBoolClose(emit) {
      this.valueBoolClose = emit;
    },
    emit() {
      this.getAll();
      this.findInvoiceId(this.invoice, "edit");
    },
    edit(index) {
      this.dialog = true;
      this.invoice.inv_id = String(this.desserts[index].inv_id);
      this.invoice.inv_nameclient = this.desserts[index].inv_nameclient;
      this.invoice.inv_date = this.customFormatter(this.desserts[index].inv_date);
      this.invoice.inv_subtotal = this.desserts[index].inv_subtotal;
      this.invoice.inv_vat = this.desserts[index].inv_vat;
      this.invoice.inv_total = this.desserts[index].inv_total;
      this.invoiceDetailData = [];
      this.findInvoiceId(this.invoice, "edit");
    },

    customFormatter(date) {
      return moment(date).format("YYYY-MM-DD");
    },
    getAll() {
      this.desserts_detail = [];
      this.invoiceService
        .getAll()
        .then((data) => {
          for (let index = 0; index < data.data.length; index++) {
            const element = data.data[index];
            const date = this.customFormatter(element.inv_date);
            data.data[index].inv_date = date;
          }
          this.desserts = data.data;
        })
        .catch((error) => {
          this.alertToastr(
            "Failed",
            "Failed",
            error,
            true,
            4000,
            true,
            "toast-top-center",
            "error",
            true,
            {},
            ["animated", "zoomInUp"],
            "",
            null
          );
        });
    },
    removeItem(index) {
      this.desserts_detail.splice(index, 1);
    },
    viewDetail(index) {
      this.invoice = this.desserts[index];
      this.findInvoiceId(this.invoice, "view");
    },
    findInvoiceId(invoice, view) {
      this.invoiceDetailService.findInvoiceId(invoice.inv_id).then((data) => {
        if (data.data.length != 0) {
          if (view == "view") {
            this.desserts_detail = data.data;
          } else if (view == "edit") {
            this.invoiceDetailData = data.data;
          }
        } else {
          this.desserts_detail = [];
          this.invoiceDetailData = [];
        }
      });
    },
    onDelete(index) {
      const element = this.desserts[index];
      this.alertToastr(
        "Info",
        "Information",
        "Do you want to delete the invoice? If you do not want to delete the invoice, click on the alert to cancel.",
        true,
        3500,
        true,
        "toast-top-center",
        "info",
        true,
        {},
        ["animated", "zoomInUp"],
        "delete",
        element
      );
    },
    alertToastr(
      name,
      title,
      data,
      clickClose,
      timeout,
      progressbar,
      position,
      type,
      preventDuplicates,
      style,
      classNames,
      call,
      element
    ) {
      this.$toastr.Add({
        name: name, //, // this is give you ability to use removeByName method
        title: title, //, // Toast Title
        msg: data, // Toast Message
        clickClose: clickClose, //, // Click Close Disable
        timeout: timeout, //, // Remember defaultTimeout is 5 sec.(5000) in this case the toast won't close automatically
        progressbar: progressbar, //, // Manually update progress bar value later; null (not 0) is default
        position: position, //, // Toast Position.
        type: type, //, // Toast type,
        preventDuplicates: preventDuplicates, //, //Default is false,
        style: style, //{}, // bind inline style to toast  (check [Vue doc](https://vuejs.org/v2/guide/class-and-style.html#Binding-Inline-Styles) for more examples)
        classNames: classNames, //,
        onCreated: () => {
          if (call == "delete") {
            this.valueBoolonClicked = true;
          }
        },
        onClicked: () => {
          if (call == "delete") {
            this.valueBoolonClicked = false;
          }
        },
        onClosed: () => {
          if (call == "delete") {
            if (this.valueBoolonClicked == true) {
              this.invoiceService
                .delete(parseInt(element.inv_id))
                .then((data) => {
                  if (data.status == 200) {
                    this.alertToastr(
                      "Successful",
                      "Successful",
                      data.data,
                      false,
                      3500,
                      true,
                      "toast-top-center",
                      "success",
                      true,
                      {},
                      ["animated", "zoomInUp"],
                      "",
                      null
                    );
                    this.getAll();
                  }
                })
                .catch((error) => {
                  this.alertToastr(
                    "Failed",
                    "Failed",
                    error.response.data,
                    false,
                    3500,
                    true,
                    "toast-top-center",
                    "error",
                    true,
                    {},
                    ["animated", "zoomInUp"],
                    "",
                    null
                  );
                });
            }
          }
        },
      });
    },
  },
};
</script>

<style>
.close {
  padding: 0px;
  text-align: right;
  width: 88.5%;
}
.vToolbar {
  padding: 0px 6px 0px 4px;
}
</style>