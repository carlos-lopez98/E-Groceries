import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import GroceryItemClient from "../api/groceryItemClient";

class CustomerAccessPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onGetAll', 'renderGroceryItem', 'renderAllGroceryItems'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('get-by-name-form').addEventListener('submit', this.onGet);
        let button = document.getElementById('btn');
        button.addEventListener('click', this.onGetAll);
        this.client = new GroceryItemClient();

        this.dataStore.addChangeListener(this.renderGroceryItem)
        this.dataStore.addChangeListener(this.renderAllGroceryItems)
    }

    // Render Methods --------------------------------------------------------------------------------------------------
    // Updates needed
   async renderGroceryItem() {
        let resultArea = document.getElementById("result-info");

        const groceries = this.dataStore.get("groceries");

        if (groceries){
            resultArea.innerHTML = `<ul>
                <div>Product Name: ${groceries.groceryProductName}</div><br>
                <div>Department: ${groceries.groceryProductDepartment}</div><br>
                <div>Price: $${groceries.groceryProductPrice}</div><br>
                <div>Product Type: ${groceries.groceryType}</div><br>
                <div>In Stock?: ${groceries.inStock}</div>
                </ul>
            `
              }
             else {
            resultArea.innerHTML = "No Grocery Items";
        }
    }

    async renderAllGroceryItems() {
        let resultArea = document.getElementById("result-info");

        const groceries = this.dataStore.get("groceries");
            let myHTML = "";
        if (groceries) {
            for (let grocery of groceries) {
                myHTML += `
                              <div>

                           Product Name: ${grocery.groceryProductName}<br><br>
                           Department: ${grocery.groceryProductDepartment}<br><br>
                          Price: $${grocery.groceryProductPrice}<br><br>
                           Product Type: ${grocery.groceryType}<br><br>
                            In Stock?: ${grocery.inStock}
                             </div>
                             <br>
                              `

            }
            myHTML += ""
            resultArea.innerHTML = myHTML;
        } else {
            resultArea.innerHTML = "No Groceries";
        }
    }
    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let name = document.getElementById("name-field").value;
          this.dataStore.set("groceries", null);

        let result = await this.client.getGroceryItem(name, this.errorHandler);

          this.dataStore.set("groceries", result);

        if (result) {
            this.showMessage(`Got ${result.groceryProductName}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onGetAll(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let result = await this.client.getAllGroceryItems(this.errorHandler);
        this.dataStore.set("groceries", result);

        if (result) {
            this.showMessage(`Got All Items!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */

const main = async () => {
    const customerAccessPage = new CustomerAccessPage();
    customerAccessPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
