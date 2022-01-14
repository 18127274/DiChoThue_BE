using API_PTUD_R5.Model;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;

namespace API_PTUD_R5.Service
{
    public class PHANLOAIService
    {
        private readonly IMongoCollection<PHANLOAI> _books;

        // <snippet_BookServiceConstructor>
        public PHANLOAIService(IDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);
            _books = database.GetCollection<PHANLOAI>(settings.PhanLoaiCollectionName);
        }
        // </snippet_BookServiceConstructor>

        public List<PHANLOAI> Get() =>
            _books.Find(book => true).ToList();

        public PHANLOAI Get(long id) =>
            _books.Find<PHANLOAI>(book => book.Id == id).FirstOrDefault();

        public PHANLOAI Create(PHANLOAI book)
        {
            book.Id = _books.AsQueryable().Count() + 1;
            _books.InsertOne(book);
            return book;
        }

        public void Update(long id, PHANLOAI bookIn) =>
            _books.ReplaceOne(book => book.Id == id, bookIn);

        public void Remove(PHANLOAI bookIn) =>
            _books.DeleteOne(book => book.Id == bookIn.Id);

        public void Remove(long id) =>
            _books.DeleteOne(book => book.Id == id);
    }
}
// </snippet_BookServiceClass>

