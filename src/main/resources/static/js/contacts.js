console.log("Hello");

const viewContactModel = document.getElementById("view_contact_modal");

// Modal instance
const options = {
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 fixed inset-0 z-40",
  closable: true
};
const instanceOptions = { id: "view_contact_modal", override: true };
const contactModal = new Modal(viewContactModel, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

// Load contact data by ID
async function loadContactData(id) {
  try {
    console.log("Loading contact ID:", id);

    const data = await (
      await fetch(`http://localhost:8080/api/contacts/${id}`)
    ).json();

    console.log("Contact Data:", data);

    // Fill modal fields
    document.getElementById("contact_name").innerText = data.name || "N/A";
    document.getElementById("contact_email").innerText = data.email || "N/A";
    document.getElementById("contact_phone").innerText = data.phoneNumber || "N/A";

    const website = document.getElementById("contact_website");
    website.innerText = data.websiteLink || "";
    website.href = data.websiteLink || "#";

    const linkedin = document.getElementById("contact_linkedin");
    linkedin.innerText = data.linkedInLink || "";
    linkedin.href = data.linkedInLink || "#";

    document.getElementById("contact_picture").src =
      data.picture || "https://via.placeholder.com/100";

    document.getElementById("contact_favorite").innerText =
      data.favorite ? "Yes" : "No";

    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}





async function deleteContact(contactId) {
    if (!confirm("Are you sure you want to delete this contact?")) {
        return;
    }

    try {
        const response = await fetch(`/api/contacts/${contactId}`, {
            method: "DELETE"
        });

        if (response.ok) {
            alert("Contact deleted successfully!");
            // Option 1: Reload the page
            location.reload();
            
            // Option 2 (better): Remove row from DOM without reload
            // document.getElementById(`row-${contactId}`).remove();
        } else {
            alert("Failed to delete contact.");
        }
    } catch (error) {
        console.error("Error deleting contact:", error);
        alert("Something went wrong.");
    }
}
