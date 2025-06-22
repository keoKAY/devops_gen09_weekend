import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Button from './components/common/Button';
import Card from './components/common/Card';

function App() {
  const [products, setProducts] = useState([]);
  const [showModal , setShowModal] = useState(false);
  const [newProduct, setNewProduct] = useState({
    title: '',
    description: '',
    price: '',
    imageUrl: ''
  });
  const handleInputChange = (e)=>{
    setNewProduct({...newProduct , [e.target.name]: e.target.value})
  }

  const handleAddProduct = async ( )=>{
    try{
      await axios.post("http://localhost:8080/api/v1/products", newProduct);
      setShowModal(false);
      getProducts()
      .then((data) => {
        console.log(data);
        setProducts(data.payload);
      })
    }catch(err){
      console.error("Error adding product:", err);
    }
  }
  const getProducts = async () => {
    const response = await axios.get("http://localhost:8080/api/v1/products");
    return response.data;
  }
  useEffect(() => {
    getProducts()
      .then((data) => {
        console.log(data);
        setProducts(data.payload);
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
      });
  }, [])
  return (
    <div className='container my-5'>
      <h2 className>Display All The Products </h2>
      <p>These are the products from our sample API </p>
      <hr />
      <button className='btn-success btn' 
      onClick={()=> setShowModal(true)}>Add New Product</button>
      <div className="d-flex my-3">
        <div className="d-flex flex-wrap gap-3">
          {Array.isArray(products) && products?.map((product) => (
            <Card key={product.id}
              price={product.price}
              title={product.title}
              description={product.description}
              image={product.imageUrl} />
          ))}
        </div>

      </div>
      {showModal && (
        <div className="modal show d-block" tabIndex="-1" role="dialog">
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Add Product</h5>
                <button type="button" className="btn-close" onClick={() => setShowModal(false)}></button>
              </div>
              <div className="modal-body">
                <input type="text" className="form-control mb-2" placeholder="Title" name="title" onChange={handleInputChange} />
                <input type="text" className="form-control mb-2" placeholder="Description" name="description" onChange={handleInputChange} />
                <input type="number" className="form-control mb-2" placeholder="Price" name="price" onChange={handleInputChange} />
                <input type="text" className="form-control mb-2" placeholder="Image URL" name="imageUrl" onChange={handleInputChange} />
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>Close</button>
                <button type="button" className="btn btn-primary" onClick={handleAddProduct}>Save changes</button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
