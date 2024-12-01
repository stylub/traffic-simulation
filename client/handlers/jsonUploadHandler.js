export function handleJsonUpload(jsonFile, setJsonObject) {
  return (e) => {
    e.preventDefault();

    const file = jsonFile.files[0];
    const reader = new FileReader();

    reader.onload = (event) => {
      try {
        const jsonObject = JSON.parse(event.target.result);
        setJsonObject(jsonObject);
        console.log(jsonObject);
      } catch (error) {
        console.error("Error parsing JSON:", error);
        Window.alert("Error parsing JSON:", error);
      }
    };

    reader.onerror = (error) => {
      console.error("Error reading file:", error);
      Window.alert("Error reading file:", error);
    };

    if (file) {
      reader.readAsText(file);
    } else {
      console.error("No file selected.");
      Window.alert("No file selected.");
    }
  };
}
