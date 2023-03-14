// import axios from 'axios';
// import { useState } from 'react';
// import { toast } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';
// import { Form, Button } from 'react-bootstrap';
// import '../css/AddProduct.css';

// function AddProduct() {
//   const [product, setProduct] = useState({
//     catId: '1',
//     name: '',
//     price: '',
//     description: '',
//     quantity: '',
//     expiryDate: '',
//   });

//   const handleInputChange = (event) => {
//     setProduct({
//       ...product,
//       [event.target.name]: event.target.value,
//     });
//   };

//   const handleSubmit = (event) => {
//     event.preventDefault();

//     const supplier = JSON.parse(sessionStorage.getItem('supplier'));
//     const supplierId = supplier.id;

//     axios.post(`http://localhost:8080/supplier/add/product?supplierId=${supplierId}&quantity=${product.quantity}`, {
//       catId: product.catId,
//       name: product.name,
//       price: product.price,
//       description: product.description,
//       expiryDate: product.expiryDate,
//     })
//       .then(response => {
       
//         console.log(response.data.message);
//         toast.success(response.data.message);
//         setProduct({
//           catId: '1',
//           name: '',
//           price: '',
//           description: '',
//           quantity: '',
//           expiryDate: '',
//         });
//       })
//       .catch(error => {
//         console.log(error);
//         toast.error(error.message);
//       });
//   };

//   return (
//     <div className="add-product">
//       <Form onSubmit={handleSubmit}>
//         <Form.Group controlId="formCategory">
//           <Form.Label>Category:</Form.Label>
//           <Form.Control as="select" name="catId" value={product.catId} onChange={handleInputChange}>
//             <option value="1">Fruit</option>
//             <option value="2">Vegetable</option>
//           </Form.Control>
//         </Form.Group>
//         <Form.Group controlId="formName">
//           <Form.Label>Name:</Form.Label>
//           <Form.Control type="text" name="name" value={product.name} onChange={handleInputChange} />
//         </Form.Group>
//         <Form.Group controlId="formPrice">
//           <Form.Label>Price:</Form.Label>
//           <Form.Control type="number" name="price" value={product.price} onChange={handleInputChange} />
//         </Form.Group>
//         <Form.Group controlId="formDescription">
//           <Form.Label>Description:</Form.Label>
//           <Form.Control as="textarea" name="description" value={product.description} onChange={handleInputChange} />
//         </Form.Group>
//         <Form.Group controlId="formExpiryDate">
//           <Form.Label>Expiry Date:</Form.Label>
//           <Form.Control type="date" name="expiryDate" value={product.expiryDate} onChange={handleInputChange} />
//         </Form.Group>
//         <Form.Group controlId="formQuantity">
//           <Form.Label>Quantity:</Form.Label>
//           <Form.Control type="number" name="quantity" value={product.quantity} onChange={handleInputChange} />
//         </Form.Group>
//         <Button variant="primary" type="submit">
//           Submit
//         </Button>
//       </Form>
//     </div>
//   );
// }

// export default AddProduct;
import axios from 'axios';
import { useState } from 'react';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Form, Button } from 'react-bootstrap';
import '../css/AddProduct.css';

function AddProduct() {
  const [product, setProduct] = useState({
    catId: '1',
    name: '',
    price: '',
    description: '',
    quantity: '',
    expiryDate: '',
    image: null,
  });

  const handleInputChange = (event) => {
    setProduct({
      ...product,
      [event.target.name]: event.target.value,
    });
  };

  const handleImageChange = (event) => {
    setProduct({
      ...product,
      image: event.target.files[0],
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const supplier = JSON.parse(sessionStorage.getItem('supplier'));
    const supplierId = supplier.id;

    const formData = new FormData();
    formData.append('image', product.image);
    formData.append('catId', product.catId);
    formData.append('name', product.name);
    formData.append('price', product.price);
    formData.append('description', product.description);
    formData.append('expiryDate', product.expiryDate);
    formData.append('quantity', product.quantity);

    axios.post(`http://localhost:8080/supplier/add/product?supplierId=${supplierId}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
      .then(response => {
       
        console.log(response.data.message);
        toast.success(response.data.message);
        setProduct({
          catId: '1',
          name: '',
          price: '',
          description: '',
          quantity: '',
          expiryDate: '',
          image: null,
        });
      })
      .catch(error => {
        
        console.log(error);
        toast.error(error.message);
      });
  };

  return (
    <div className="add-product">
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formCategory">
          <Form.Label>Category:</Form.Label>
          <Form.Control as="select" name="catId" value={product.catId} onChange={handleInputChange}>
            <option value="1">Fruit</option>
            <option value="2">Vegetable</option>
          </Form.Control>
        </Form.Group>
        <Form.Group controlId="formName">
          <Form.Label>Name:</Form.Label>
          <Form.Control type="text" name="name" value={product.name} onChange={handleInputChange} />
        </Form.Group>
        <Form.Group controlId="formPrice">
          <Form.Label>Price:</Form.Label>
          <Form.Control type="number" name="price" value={product.price} onChange={handleInputChange} />
        </Form.Group>
        <Form.Group controlId="formDescription">
          <Form.Label>Description:</Form.Label>
          <Form.Control as="textarea" name="description" value={product.description} onChange={handleInputChange} />
        </Form.Group>
        <Form.Group controlId="formExpiryDate">
          <Form.Label>Expiry Date:</Form.Label>
          <Form.Control type="date" name="expiryDate" value={product.expiryDate} onChange={handleInputChange} />
        </Form.Group>
        <Form.Group controlId="formQuantity">
          <Form.Label>Quantity:</Form.Label>
          <Form.Control type="number" name="quantity" value={product.quantity} onChange={handleInputChange} />
        </Form.Group>
        <Form.Group controlId="formImage">
          <Form.Label>Image</Form.Label>
          <Form.Control type="file" name="image" accept="image/*" onChange={handleImageChange} />
        </Form.Group>
        <br/>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
}

export default AddProduct;
