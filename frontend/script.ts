/// <reference lib="es2015" />

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("markdownForm") as HTMLFormElement;
    const successMessage = document.getElementById("successMessage") as HTMLElement;
    const pasteLink = document.getElementById("pasteLink") as HTMLAnchorElement;
  
    form.onsubmit = async (event) => {
      event.preventDefault();
  
      const title = (document.getElementById("title") as HTMLInputElement).value;
      const content = (document.getElementById("content") as HTMLTextAreaElement).value;
  
      try {
        // Replace 'http://localhost:8080' with your actual backend URL
        const response = await fetch("http://localhost:8080/api/pastes", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ title, content }),
        });
  
        if (response.ok) {
          // Parse the response as JSON
          const data = await response.json();
  
          // Access the uniqueID from the response body
          const uniqueID = data.uniqueID;
  
          // Show success message with the paste link
          successMessage.style.display = "block";
          pasteLink.href = `/paste/${uniqueID}`;
          pasteLink.textContent = "here";
        } else {
          console.error("Failed to save paste");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    };

    // Add event listener to fetch paste by ID
  const fetchPasteForm = document.getElementById("fetchPasteForm") as HTMLFormElement;
  const pasteIdInput = document.getElementById("pasteIdInput") as HTMLInputElement;
  const pasteContentDiv = document.getElementById("pasteContent") as HTMLElement;

  fetchPasteForm.onsubmit = async (event) => {
    event.preventDefault();

    const pasteId = pasteIdInput.value;

    try {
      const response = await fetch(`http://localhost:8080/api/pastes/${pasteId}`, {
        method: "GET",
        headers: {
          "Accept": "application/json",
        },
      });

      if (response.ok) {
        const data = await response.json();
        pasteContentDiv.textContent = `Title: ${data.title}\n\nContent:\n${data.content}`;
      } else {
        console.error("Failed to fetch paste");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };
});

  