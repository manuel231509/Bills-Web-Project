<template>
  <div>
    <br />
    <div class="container elevation-20">
      <v-container fluid>
        <v-row dense>
          <!-- -------------- Invoice form -------------- -->
          <v-col :cols="6">
            <v-card height="100%">
              <v-card-title
                style="text-transform: uppercase"
                class="justify-center"
              >
                INVOICE UPDATE FORM
              </v-card-title>
              <v-spacer></v-spacer>
              <div class="container">
                <div class="container">
                  <v-form
                    ref="formBills"
                    v-model="validInvoice"
                    lazy-validation
                  >
                    <v-text-field
                      :disabled="true"
                      id="inv_id"
                      v-model="invoice.inv_id"
                      :rules="invoiceRules.inv_idRules"
                      class="inputinv_id"
                      label="Bill Number"
                      type="number"
                      min="1"
                      required
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                      v-model="invoice.inv_nameclient"
                      :rules="invoiceRules.inv_nameclientRules"
                      label="Client Name"
                      type="text"
                      required
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                      :disabled="true"
                      v-model="invoice.inv_date"
                      :rules="invoiceRules.inv_dateRules"
                      label="Date"
                      type="date"
                      required
                    ></v-text-field>
                  </v-form>
                </div>
              </div>
              <br />
            </v-card>
          </v-col>
          <!-- -------------- End -------------- -->

          <!-- -------------- Detail form -------------- -->
          <v-col :cols="6">
            <v-card>
              <v-card-title
                style="text-transform: uppercase"
                class="justify-center"
              >
                Detail registration form
              </v-card-title>
              <v-spacer></v-spacer>
              <div class="container">
                <div class="container">
                  <v-form ref="detForm" v-model="validDetail" lazy-validation>
                    <v-text-field
                      :disabled="disInvDetItem"
                      id="invdet_itemid"
                      v-model="invoiceDetail.invdet_itemid"
                      class="inputinvdet_itemid"
                      :rules="invoiceDetailRules.invdet_itemidRules"
                      label="Id Article"
                      type="number"
                      min="1"
                      required
                    ></v-text-field>

                    <v-text-field
                      id="invdet_itemname"
                      v-model="invoiceDetail.invdet_itemname"
                      :rules="invoiceDetailRules.invdet_itemnameRules"
                      label="Name Article"
                      required
                    ></v-text-field>

                    <v-text-field
                      id="invdet_quantityitem"
                      v-model="invoiceDetail.invdet_quantityitem"
                      :rules="invoiceDetailRules.invdet_quantityitemRules"
                      label="Quantity Items"
                      type="number"
                      min="1"
                      required
                    ></v-text-field>

                    <v-text-field
                      id="invdet_itemvalue"
                      v-model="invoiceDetail.invdet_itemvalue"
                      :rules="invoiceDetailRules.invdet_itemvalueRules"
                      label="Item Value"
                      type="number"
                      min="1"
                      required
                    ></v-text-field>

                    <div class="text-center">
                      <v-btn
                        :disabled="!validDetail"
                        color="success"
                        class="mr-4"
                        @click="validateDetail"
                      >
                        {{ addMessageButtonDetail }}
                      </v-btn>

                      <v-btn
                        :disabled="!validInvoiceDetailReset"
                        color="error"
                        class="mr-4"
                        @click="resetForm_ValidationDetail"
                      >
                        Reset Form And Validation</v-btn
                      >
                      <div v-if="addMessageInvDet">
                        <br />
                        <v-alert dense type="warning">
                          {{ menssage }}
                        </v-alert>
                      </div>
                    </div>
                  </v-form>
                </div>
              </div>
            </v-card>
          </v-col>
          <!-- -------------- End -------------- -->

          <v-col :cols="12">
            <v-card>
              <v-spacer></v-spacer>
              <div class="container">
                <div class="container">
                  <v-data-table
                    :headers="headers"
                    :items="desserts"
                    :items-per-page="5"
                    class="elevation-2"
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
                        <td>
                          <v-btn
                            :disabled="!valueBoolEdit"
                            color="error"
                            @click="edit(row.index)"
                            fab
                            small
                          >
                            <v-icon small color="darken-2">mode_edit</v-icon>
                          </v-btn>
                        </td>
                        <td>
                          <v-btn
                            :disabled="!valueBoolRemove"
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
                  <br />
                  <v-form
                    ref="formBills1"
                    v-model="validInvoice"
                    lazy-validation
                  >
                    <v-text-field
                      id="subTotal"
                      :disabled="true"
                      v-model="invoice.inv_subtotal"
                      :rules="invoiceRules.inv_subtotalRules"
                      label="Subtotal"
                      type="number"
                      required
                    ></v-text-field>

                    <v-text-field
                      v-model="invoice.inv_vat"
                      :rules="invoiceRules.inv_vatRules"
                      class="inputVAT"
                      id="VAT"
                      label="VAT"
                      type="number"
                      min="1"
                      max="100"
                      @keydown="
                        calculateTotalWithVat(
                          invoice.inv_subtotal,
                          invoice.inv_vat
                        )
                      "
                      required
                    >
                    </v-text-field>

                    <v-text-field
                      :disabled="true"
                      v-model="invoice.inv_total"
                      :rules="invoiceRules.inv_totalRules"
                      label="Total"
                      type="number"
                      required
                    ></v-text-field>

                    <div class="text-center">
                      <v-btn
                        :disabled="!validInvoice || !valueBoolEditInoiceDetail"
                        color="success"
                        class="mr-4"
                        @click="validateInvoice"
                      >
                        Finish Invoice
                      </v-btn>
                      <v-btn
                        :disabled="!validInvoiceReset"
                        color="error"
                        class="mr-4"
                        @click="resetForm_ValidationInvoice"
                      >
                        Reset Form And Validation
                      </v-btn>
                      <transition name="fade">
                        <div v-if="addMessageInv">
                          <br />
                          <v-alert dense type="warning">
                            {{ menssage }}
                          </v-alert>
                        </div>
                        <div v-if="addMessageInvSuccess">
                          <br />
                          <v-alert dense type="success">
                            {{ menssage }}
                          </v-alert>
                        </div>
                      </transition>
                    </div>
                  </v-form>
                </div>
              </div>
              <br />
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
    <br />
  </div>
</template>

<script>
import InvoiceService from "../Service/InvoiceService";
import InvoiceDetailService from "../Service/InvoiceDetailService";
export default {
  data: () => ({
    addMessageInvDet: false,
    addMessageInv: false,
    addMessageInvSuccess: false,
    disInvDetItem: false,
    valueBoolEditInoiceDetail: true,
    valueBoolEdit: true,
    valueBoolRemove: true,
    valueBoolonClicked: true,
    addMessageButtonDetail: "Add Item",
    cont: 0,
    menssage: "",
    // ------------ Invoice form validations ------------

    validInvoice: true,
    validInvoiceReset: true,
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
    // ------------ END ------------

    // ------------ Detail form validations ------------
    validDetail: true,
    validInvoiceDetailReset: true,
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

    headers: [
      {
        text: "Id Article",
        align: "start",
        sortable: false,
        value: "invdet_itemid",
      },
      { text: "Name Article", value: "invdet_itemname" },
      { text: "Quantity Items", value: "invdet_quantityitem" },
      { text: "Item Value", value: "invdet_itemvalue" },
      { text: "Edit", value: "editItem" },
      { text: "Remove", value: "removeItem" },
    ],

    // ------------ END ------------
  }),
  invoiceService: null,
  invoiceDetailService: null,
  created() {
    this.invoiceService = new InvoiceService();
    this.invoiceDetailService = new InvoiceDetailService();
  },

  props: {
    invoice: {
      inv_id: null,
      inv_nameclient: String,
      inv_date: Date,
      inv_subtotal: "0",
      inv_vat: "1",
      inv_total: "0",
    },
    desserts: null,
  },

  methods: {
    calculateTotalWithVat(subTotal, VAT) {
      var e = window.event;
      if (
        (e.keyCode == 38 ||
          e.keyCode == 40 ||
          (e.keyCode >= 48 && e.keyCode <= 57)) &&
        this.desserts
      ) {
        VAT = parseInt(VAT);
        if (e.keyCode == 38) {
          VAT++;
          this.cont = 0;
        } else if (e.keyCode == 40) {
          if (this.cont == 0) {
            VAT--;
            if (VAT == 1) {
              this.cont = 1;
            }
          }
        }
        var valueVAT = parseInt(subTotal) + (parseInt(subTotal) * VAT) / 100;
        this.invoice.inv_total = String(parseInt(valueVAT));
      }
    },
    // ------------ Invoice form validations ------------
    validateInvoice() {
      if (this.$refs.formBills.validate() && this.$refs.formBills1.validate()) {
        this.update();
      }
    },
    resetInvoice() {
      if (this.desserts.length == 0) {
        this.$refs.formBills.reset();
        this.invoice.inv_subtotal = "0";
        this.invoice.inv_vat = "1";
        this.invoice.inv_total = "0";
      }
    },
    resetValidationInvoice() {
      this.$refs.formBills.resetValidation();
      this.$refs.formBills1.resetValidation();
    },
    resetForm_ValidationInvoice() {
      this.resetInvoice();
      this.resetValidationInvoice();
      this.addMessageInv = false;
      this.addMessageInvSuccess = false;
    },
    // ------------ END ------------

    validateDetail() {
      if (this.$refs.detForm.validate()) {
        this.addItemTable();
      }
    },
    resetDetail() {
      this.$refs.detForm.reset();
    },
    resetValidationDetail() {
      this.$refs.detForm.resetValidation();
    },
    resetForm_ValidationDetail() {
      this.resetValidationDetail();
      if (this.addMessageButtonDetail != "Update Item") {
        this.resetDetail();
        this.addMessageInvDet = false;
        this.disInvDetItem = false;
        this.addMessageButtonDetail = "Add Item";
      }
    },
    addItemTable() {
      var invdet_itemid = document.getElementById("invdet_itemid").value;
      var invdet_itemname = document.getElementById("invdet_itemname").value;
      var invdet_quantityitem = parseInt(
        document.getElementById("invdet_quantityitem").value
      );
      var invdet_itemvalue = parseInt(
        document.getElementById("invdet_itemvalue").value
      );

      var invdet_inv_id = document.getElementById("inv_id").value;
      var saber = false;
      var boolKnowAdd = this.knowAddProduct(invdet_itemid, invdet_itemname);
      if (boolKnowAdd || invdet_inv_id.length == 0) {
        saber = true;
        this.addMessageInvDet = true;
        if (boolKnowAdd) {
          this.menssage = "The item you are adding is already added";
        } else if (invdet_inv_id.length == 0) {
          this.menssage = "You must first add the invoice number";
        }
      } else {
        this.addMessageInvDet = false;
        var subtot = parseInt(document.getElementById("subTotal").value);
        this.sumSubTotal = subtot + invdet_quantityitem * invdet_itemvalue;
        this.invoice.inv_subtotal = String(this.sumSubTotal);
        var VAT = parseInt(document.getElementById("VAT").value);
        var valueVAT = this.sumSubTotal + (this.sumSubTotal * VAT) / 100;
        this.invoice.inv_total = String(parseInt(valueVAT));
        this.disInvDetItem = false;
        this.cont = 1;
        var invdet_id = 0;
        if (this.addMessageButtonDetail == "Update Item") {
          this.valueBoolEdit = true;
          this.valueBoolRemove = true;
          this.valueBoolEditInoiceDetail = true;
          this.validInvoiceReset = true;
          this.$emit("updateValueBoolClose", true);
          invdet_id = this.invoiceDetail.invdet_id;
          this.desserts.push({
            invdet_id,
            invdet_inv_id,
            invdet_itemid,
            invdet_itemname,
            invdet_quantityitem,
            invdet_itemvalue,
          });
        } else {
          this.desserts.push({
            invdet_id,
            invdet_inv_id,
            invdet_itemid,
            invdet_itemname,
            invdet_quantityitem,
            invdet_itemvalue,
          });
        }

        this.addMessageButtonDetail = "Add Item";
        this.resetDetail();
      }
      return saber;
    },
    removeItem(index) {
      const element = this.desserts[index];
      this.sumSubTotal = element.invdet_quantityitem * element.invdet_itemvalue;
      var restSubTotal =
        parseInt(document.getElementById("subTotal").value) - this.sumSubTotal;
      this.invoice.inv_subtotal = String(restSubTotal);
      var VAT = parseInt(document.getElementById("VAT").value);
      var valueVAT =
        parseInt(restSubTotal) + (parseInt(restSubTotal) * VAT) / 100;
      this.invoice.inv_total = String(parseInt(valueVAT));
      this.desserts.splice(index, 1);
    },
    edit(index) {
      this.valueBoolEditInoiceDetail = false;
      this.validInvoiceReset = false;
      this.valueBoolEdit = false;
      this.valueBoolRemove = false;
      this.$emit("updateValueBoolClose", false);
      const element = this.desserts[index];
      this.sumSubTotal = element.invdet_quantityitem * element.invdet_itemvalue;
      var restSubTotal =
        parseInt(document.getElementById("subTotal").value) - this.sumSubTotal;
      this.invoice.inv_subtotal = String(restSubTotal);
      var VAT = parseInt(document.getElementById("VAT").value);
      var valueVAT =
        parseInt(restSubTotal) + (parseInt(restSubTotal) * VAT) / 100;
      this.invoice.inv_total = String(parseInt(valueVAT));
      this.invoiceDetail.invdet_id = element.invdet_id;
      this.invoiceDetail.invdet_inv_id = element.invdet_inv_id;
      this.invoiceDetail.invdet_itemid = String(element.invdet_itemid);
      this.invoiceDetail.invdet_itemname = element.invdet_itemname;
      this.invoiceDetail.invdet_quantityitem = element.invdet_quantityitem;
      this.invoiceDetail.invdet_itemvalue = element.invdet_itemvalue;
      this.disInvDetItem = true;
      this.addMessageButtonDetail = "Update Item";
      this.desserts.splice(index, 1);
    },
    knowAddProduct(invdet_itemid, invdet_itemname) {
      var saber = false;
      for (let index = 0; index < this.desserts.length; index++) {
        const element = this.desserts[index];
        if (
          invdet_itemid == element.invdet_itemid ||
          (invdet_itemid == element.invdet_itemid &&
            invdet_itemname == element.invdet_itemname) ||
          invdet_itemname == element.invdet_itemname
        ) {
          saber = true;
        }
      }

      return saber;
    },
    update() {
      this.alertToastr(
        "Info",
        "Information",
        "Do you want to update the invoice? If you do not want to update the invoice, click on the alert to cancel.",
        true,
        3500,
        true,
        "toast-top-center",
        "info",
        true,
        {},
        ["animated", "zoomInUp"],
        "update",
        null,
        null
      );
    },
    onDelete(index) {
      const element = this.desserts[index];
      this.alertToastr(
        "Info",
        "Information",
        "Do you want to delete the article? If you don't want to delete the article, click on the alert to cancel.",
        true,
        3500,
        true,
        "toast-top-center",
        "info",
        true,
        {},
        ["animated", "zoomInUp"],
        "delete",
        element,
        index
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
      element,
      index
    ) {
      this.$toastr.Add({
        name: name, // this is give you ability to use removeByName method
        title: title, // Toast Title
        msg: data, // Toast Message
        clickClose: clickClose, // Click Close Disable
        timeout: timeout, // Remember defaultTimeout is 5 sec.(5000) in this case the toast won't close automatically
        progressbar: progressbar, // Manually update progress bar value later; null (not 0) is default
        position: position, // Toast Position.
        type: type, // Toast type,
        preventDuplicates: preventDuplicates, //Default is false,
        style: style, // bind inline style to toast  (check [Vue doc](https://vuejs.org/v2/guide/class-and-style.html#Binding-Inline-Styles) for more examples)
        classNames: classNames,
        onCreated: () => {
          if (call == "update" || call == "delete") {
            this.validInvoice = false;
            this.validInvoiceReset = false;
            this.validDetail = false;
            this.validInvoiceDetailReset = false;
            this.valueBoolEdit = false;
            this.valueBoolRemove = false;
            this.valueBoolonClicked = true;
          }
        },
        onClicked: () => {
          if (call == "delete" || call == "update") {
            this.validInvoice = true;
            this.validInvoiceReset = true;
            this.validDetail = true;
            this.validInvoiceDetailReset = true;
            this.valueBoolEdit = true;
            this.valueBoolRemove = true;
            this.valueBoolonClicked = false;
          }
        },
        onClosed: () => {
          if (call == "update") {
            if (this.valueBoolonClicked == true) {
              this.invoiceService
                .update(this.invoice)
                .then((res) => {
                  if (res.status == 200) {
                    this.invoiceDetailService
                      .update(this.desserts)
                      .then((data) => {
                        this.menssage = data.data;
                        this.addMessageInv = false;
                        this.addMessageInvSuccess = true;
                        this.alertToastr(
                          "Successful",
                          "Successful",
                          data.data,
                          false,
                          2000,
                          true,
                          "toast-top-center",
                          "success",
                          true,
                          {},
                          ["animated", "zoomInUp"],
                          "",
                          null,
                          null
                        );

                        this.resetForm_ValidationInvoice();
                        this.$emit("updateInvoice", false);
                        this.addMessageInvSuccess = false;
                        this.validInvoice = true;
                        this.validInvoiceReset = true;
                        this.validDetail = true;
                        this.validInvoiceDetailReset = true;
                        this.valueBoolEdit = true;
                        this.valueBoolRemove = true;
                        this.valueBoolonClicked = false;
                      });
                  }
                })
                .catch((error) => {
                  this.menssage = error.response.data;
                  this.addMessageInvSuccess = false;
                  this.addMessageInv = false;
                  this.alertToastr(
                    "Failed",
                    "Failed",
                    error.response.data,
                    false,
                    3000,
                    true,
                    "toast-top-center",
                    "error",
                    true,
                    {},
                    ["animated", "zoomInUp"],
                    "",
                    null,
                    null
                  );
                });
            }
          } else if (call == "delete") {
            if (this.valueBoolonClicked == true) {
              this.invoiceDetailService
                .delete(parseInt(element.invdet_id))
                .then((data) => {
                  if (data.status == 200) {
                    this.removeItem(index);
                    this.invoiceService
                      .update(this.invoice)
                      .then((res) => {
                        if (res.status == 200) {
                          this.alertToastr(
                            "Successful",
                            "Successful",
                            data.data,
                            false,
                            2000,
                            true,
                            "toast-top-center",
                            "success",
                            true,
                            {},
                            ["animated", "zoomInUp"],
                            "",
                            null,
                            null
                          );
                          this.validInvoice = true;
                          this.validInvoiceReset = true;
                          this.validDetail = true;
                          this.validInvoiceDetailReset = true;
                          this.valueBoolEdit = true;
                          this.valueBoolRemove = true;
                          this.valueBoolonClicked = false;
                        }
                      })
                      .catch((error) => {
                        this.menssage = error.response.data;
                        this.addMessageInvSuccess = false;
                        this.addMessageInv = false;
                        this.alertToastr(
                          "Failed",
                          "Failed",
                          error.response.data,
                          false,
                          3000,
                          true,
                          "toast-top-center",
                          "error",
                          true,
                          {},
                          ["animated", "zoomInUp"],
                          "",
                          null,
                          null
                        );
                      });
                  }
                })
                .catch((error) => {
                  console.log("error:  " + error);
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
.fade-enter-active,
.fade-leave-active {
  transition: opacity 2.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>