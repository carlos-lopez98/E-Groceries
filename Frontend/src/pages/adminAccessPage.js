import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import GroceryItemClient from "../api/groceryItemClient";

class AdminAccessPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onGetAll', 'onCreate', 'onDelete', 'renderAllGroceryItems'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('get-by-name-form').addEventListener('submit', this.onGet);
        document.getElementById('get-all-grocery-items-button').addEventListener('click', this.onGetAll);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        document.getElementById('delete-grocery-item-form').addEventListener('submit', this.onDelete);
        this.client = new GroceryItemClient();

        this.dataStore.addChangeListener(this.renderAllGroceryItems)

    }

    // Render Methods --------------------------------------------------------------------------------------------------
    async renderAllGroceryItems() {
        let resultArea = document.getElementById("result-info");

        let createdGroceries = this.dataStore.get("groceries");
        let groceries = this.dataStore.get("allGroceries");
        let deletedItem = this.dataStore.get("groceryDeleted");

        if (createdGroceries) {
            resultArea.innerHTML = `<ul>
                <br>
                <div>Product Name: ${createdGroceries.groceryProductName}</div>
                <div>Department: ${createdGroceries.groceryProductDepartment}</div>
                <div>Price: $${createdGroceries.groceryProductPrice}</div>
                <div>Expiration Date: ${createdGroceries.groceryExpirationDate}</div>
                <div>Product Type: ${createdGroceries.groceryType}</div>
                <div>In Stock?: ${createdGroceries.inStock}</div>
                <div>Quantity Available: ${createdGroceries.quantityAvailable}</div>
                <div>Discounted?: ${createdGroceries.discount}</div>
                </ul>`;
        }

        if (deletedItem) {
            resultArea.innerHTML = "Item Deleted";
        }

        if (groceries.length > 1) {
            let myHTML = "";
            for (let grocery of groceries) {
                myHTML += `
                    <div>Product Name: ${grocery.groceryProductName}<br><br>
                    Department: ${grocery.groceryProductDepartment}<br><br>
                    Price: $${grocery.groceryProductPrice}<br><br>
                    Expiration Date: ${grocery.groceryExpirationDate}<br><br>
                    Product Type: ${grocery.groceryType}<br><br>
                    In Stock?: ${grocery.inStock}<br><br>
                    Quantity Available: ${grocery.quantityAvailable}<br><br>
                    Discounted?: ${grocery.discount}<br><br>
                    </div>
                    <br>`;
            }
            myHTML += ""
            resultArea.innerHTML = myHTML;
        } else if (groceries.length = 1) {
            resultArea.innerHTML = `<ul>
                    <div>Product Name: ${groceries.groceryProductName}</div>
                    <div>Department: ${groceries.groceryProductDepartment}</div>
                    <div>Price: $${groceries.groceryProductPrice}</div>
                    <div>Expiration Date: ${groceries.groceryExpirationDate}</div>
                    <div>Product Type: ${groceries.groceryType}</div>
                    <div>In Stock?: ${groceries.inStock}</div>
                    <div>Quantity Available: ${groceries.quantityAvailable}</div>
                    <div>Discounted?: ${groceries.discount}</div>
                    </ul>
                <br>`;
        } else {
            resultArea.innerHTML = "No Groceries"
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------
    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let name = document.getElementById("name-field").value;
        this.dataStore.set("allGroceries", null);

        let result = await this.client.getGroceryItem(name, this.errorHandler);
        this.dataStore.set("allGroceries", result);
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
        this.dataStore.set("allGroceries", result);

        if (result) {
            this.showMessage(`Got All Items!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("groceries", null);

        let groceryProductName = document.getElementById("create-grocery-product-name-field").value;
        let groceryProductDepartment = document.getElementById("create-grocery-product-department-field").value;
        let groceryProductPrice = document.getElementById("create-grocery-product-price-field").value;
        let groceryExpirationDate = document.getElementById("create-grocery-product-expiration-date-field").value;
        let groceryType = document.getElementById("create-grocery-product-type-field").value;
        let inStock = document.getElementById("create-grocery-product-in-stock-field").value;
        let quantityAvailable = document.getElementById("create-grocery-product-quantity-field").value;
        let discount = document.getElementById("create-grocery-product-discount-field").value;

        const createdGroceryItem = await this.client.createGroceryItem(groceryProductName,
            groceryProductDepartment, groceryProductPrice, groceryExpirationDate, groceryType, inStock, quantityAvailable, discount, this.errorHandler);

        this.dataStore.set("groceries", createdGroceryItem);

        if (createdGroceryItem) {
            this.showMessage(`Created ${createdGroceryItem.groceryProductName}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("groceryDeleted", null);


        let name = document.getElementById("delete-name-field").value;

        let deletedItem = await this.client.deleteGroceryItem(name, this.errorHandler);
        this.dataStore.set("groceryDeleted", deletedItem);

        if (deletedItem) {
            this.showMessage(`Deleted ${deletedItem.groceryProductName}!`)
        } else {
            this.errorHandler("Error deleting!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const adminAccessPage = new AdminAccessPage();
    adminAccessPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
