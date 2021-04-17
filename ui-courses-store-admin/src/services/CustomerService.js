export async function getAllCustomers() {

    const response = await fetch('/courses-store/customer/search');
    console.log(JSON.stringify(response));

    return await response.json();
}

export async function createNewCustomer(newCustomer) {

    const response = await fetch('/courses-store/customer/register', {
        method: "POST",
        body: JSON.stringify(newCustomer),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });

      console.log("new customer", response);

      
}

export async function editCustomer(customerToEdit) {
    console.log(JSON.stringify(customerToEdit));
    await fetch('/courses-store/customer/update', {
        method: "PUT",
        body: JSON.stringify(customerToEdit),
        headers: {"Content-type": "application/json; charset=UTF-8"}
      });
}

export async function deleteCustomer(idCustomerToDelete) {
    console.log(JSON.stringify(idCustomerToDelete));
    await fetch('/courses-store/customer/' + idCustomerToDelete, {
        method: "DELETE"
      });
}