import React from 'react'

const Card = ({ title, description, image , price}) => {
    return (
        <div className='bg-light p-3 mx-2 rounded shadow-lg card-hover' >
            <div className="d-flex gap-3">
                <img 
                style={{ width: '200px', height: '200px' }}
                className='rounded img-fluid img-thumbnail ' 
                src={image} alt=""
                onError={(e)=>{
                    e.target.onerror = null; 
                    e.target.src ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRys2dSP963S7kuiTi0IdXOdbbcF9T2N4KMwg&s"
                }}
                />
                <div> 
                    <h2>{price}$</h2>
                    <h4>{title} </h4>
                    <p>{description} </p></div>
            </div>
        </div>
    )
}

export default Card